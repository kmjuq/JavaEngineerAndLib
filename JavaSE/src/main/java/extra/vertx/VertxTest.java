package extra.vertx;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import io.vertx.core.*;
import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.file.FileProps;
import io.vertx.core.file.FileSystem;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.NetSocket;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class VertxTest {

    private static Vertx vertx;
    private static EventBus eventBus;

    @BeforeAll
    public static void init() {
        vertx = Vertx.vertx();
        eventBus = vertx.eventBus();
    }

    @Test
    public void demo1() throws InterruptedException {
        vertx.setPeriodic(1000, id -> {
            System.out.println("timer fired!");
        });
        TimeUnit.SECONDS.sleep(10);
    }

    @Test
    public void demo2() {
        Vertx vertx = Vertx.vertx();
        FileSystem fs = vertx.fileSystem();
        String path = "/Users/kmj/WorkSpace/git/study/JavaSE/src/main/resources/yml/yml-load.yml";
        Future<FileProps> future = fs.props(path);

        future.onComplete((AsyncResult<FileProps> ar) -> {
            if (ar.succeeded()) {
                FileProps props = ar.result();
                System.out.println("File size = " + props.size());
            } else {
                System.out.println("Failure: " + ar.cause().getMessage());
            }
        });
    }

    @Test
    public void demo3() {
        FileSystem fs = vertx.fileSystem();

        Future<Void> future = fs
                .createFile("/foo")
                .compose(v -> {
                    // When the file is created (fut1), execute this:
                    return fs.writeFile("/foo", Buffer.buffer());
                })
                .compose(v -> {
                    // When the file is written (fut2), execute this:
                    return fs.move("/foo", "/bar");
                });
        future.result();
    }

    @Test
    public void demo4() {
        NetServer netServer = vertx.createNetServer();
        HttpServer httpServer = vertx.createHttpServer();

        Handler handler = (Handler<AsyncResult<NetServer>>) event -> {

        };
        Future<HttpServer> httpServerFuture = httpServer.listen(8081);
        Future<NetServer> netServerFuture = netServer.listen(8082);
        netServerFuture.onComplete(handler);
        httpServerFuture.onComplete(handler);


        Future.all(httpServerFuture, netServerFuture).onComplete(ar -> {
            if (ar.succeeded()) {
                // All servers started
                log.info("server started");
            } else {
                log.error("fail started", ar.cause());
                // At least one server failed
            }
        });
    }

    @Test
    public void demo5() throws InterruptedException {
        Future<String> future = vertx.createDnsClient().lookup("vertx.io");
        future.toCompletionStage().whenComplete((ip, err) -> {
            if (err != null) {
                log.info("Could not resolve vertx.io", err);
            } else {
                log.info("vertx.io => " + ip);
            }
        });
        TimeUnit.SECONDS.sleep(30);
    }

    @Test
    public void demo6() throws InterruptedException {
        Verticle myVerticle = new MyVerticle();
        vertx.deployVerticle(myVerticle).onComplete(res -> {
            if (res.succeeded()) {
                log.info("Deployment id is: " + res.result());
            } else {
                log.info("Deployment failed!");
            }
        });
        TimeUnit.SECONDS.sleep(30);
    }

    @Test
    public void demo7() {
        JsonObject config = new JsonObject().put("name", "tim").put("directory", "/blah");
        DeploymentOptions options = new DeploymentOptions().setConfig(config);
        vertx.deployVerticle("extra.vertx.MyVerticle", options);
    }

    @Test
    public void demo8() {
        DeploymentOptions options = new DeploymentOptions().setInstances(16);
        vertx.deployVerticle("extra.vertx.MyVerticle", options);
    }

    @Test
    public void demo9() throws InterruptedException {
        vertx.setTimer(1000, (id) -> {
            log.info("execute once");
        });
        vertx.setPeriodic(1000, (id) -> {
            log.info("execute periodic");
        });
        TimeUnit.SECONDS.sleep(5);
    }

    /**
     * 总线地址可以注册多个消费者
     * send 方法是轮询发送
     * publish 是所有消费者都发送
     */
    @Test
    public void demo10() throws InterruptedException {
        MessageConsumer<Object> consumer1 = eventBus.consumer("news.uk.sport");
        consumer1.handler(msg -> {
            log.info("1 event bus {}", msg.body());
        });
        consumer1.completionHandler(res -> {
            if (res.succeeded()) {
                log.info("the handler registered");
            } else {
                log.info("handler Registeration failed");
            }
        });

        MessageConsumer<Object> consumer2 = eventBus.consumer("news.uk.sport");
        consumer2.handler(msg -> {
            log.info("2 event bus {}", msg.body());
        });
        consumer2.completionHandler(res -> {
            if (res.succeeded()) {
                log.info("the handler registered");
            } else {
                log.info("handler Registeration failed");
            }
        });
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            eventBus.publish("news.uk.sport", "kmj is playing basketball publish");
        }, 0, 1, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            eventBus.send("news.uk.sport", "kmj is playing basketball send");
        }, 0, 1, TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(60);
    }

    @Test
    public void demo11() throws InterruptedException {
        MessageConsumer<String> consumer = eventBus.consumer("gaming");
        final int result = RandomUtil.randomInt(10, 100);
        AtomicInteger step = new AtomicInteger(0);
        consumer.handler(message -> {
            log.info(message.body());
            if (step.getAndIncrement() == 0) {
                message.reply("""
                            We are start to play game,the rule is Guess The Number
                            please input (10-100) number:
                        """);
            }
            if (step.get() > 0) {
                if (StrUtil.isNumeric(message.body())) {
                    int num = Integer.parseInt(message.body());
                    if (num > result) {
                        message.reply("""
                                Your num is bigger,please input new number
                                """);
                    } else if (num < result) {
                        message.reply("""
                                Your num is smaller,please input new number
                                """);
                    } else {
                        message.reply("""
                                Yours num is right,game end!;
                                """);
                    }
                }
            }

        });
        AtomicInteger guess = new AtomicInteger(RandomUtil.randomInt(10, 100));
        eventBus.request("gaming", "I'm coming", (Handler<AsyncResult<Message<String>>>) ar -> {
            if (ar.succeeded()) {
                Message<String> msg = ar.result();
                String body = ar.result().body();
                log.info(body);
                if (body.contains("We are start to play game")) {
                    msg.reply(String.valueOf(guess.get()));
                } else if (body.contains("Your num is bigger")) {
                    msg.reply(String.valueOf(guess.decrementAndGet()));
                } else if (body.contains("Your num is smaller")) {
                    msg.reply(String.valueOf(guess.incrementAndGet()));
                }
            }
        });
        TimeUnit.SECONDS.sleep(10);
    }

    @Test
    public void demo12() throws InterruptedException {
        VertxOptions options = new VertxOptions();
        Vertx.clusteredVertx(options, res -> {
            if (res.succeeded()) {
                Vertx vertx = res.result();
                EventBus eventBus = vertx.eventBus();
                System.out.println("We now have a clustered event bus: " + eventBus);
            } else {
                System.out.println("Failed: " + res.cause());
            }
        });
    }

    /**
     * tcp 服务端
     */
    @Test
    public void demo13() throws InterruptedException {
        NetServerOptions options = new NetServerOptions();
        options.setPort(43563);
        NetServer netServer = vertx.createNetServer(options);
        netServer.connectHandler(socket -> {
            log.info("已接入一个socket");
            socket.write("echo");
            socket.handler(buffer -> {
                log.info("读取到socket的数据 {}", new String(buffer.getBytes()));
            });
            socket.closeHandler(v -> {
                log.info("socket 关闭");
            });
        });
        netServer.listen(ar -> {
            if (ar.succeeded()) {
                log.info("net server port is {}", netServer.actualPort());
                log.info("net server is now listening");
            }
        });
        TimeUnit.SECONDS.sleep(60);
    }

    @Test
    public void demo14() throws InterruptedException {
        NetClient netClient = vertx.createNetClient();
        netClient.connect(43563, "localhost", res -> {
            if (res.succeeded()) {
                log.info("connected success");
                NetSocket result = res.result();
                result.handler(buffer -> {
                    Buffer writeBuffer = Buffer.buffer();
                    writeBuffer.appendBytes("你好呀".getBytes(StandardCharsets.UTF_8));
                    result.write(writeBuffer);
                });
            } else {
                log.info("链接失败", res.cause());
            }
        });
        TimeUnit.SECONDS.sleep(10);
    }

    /**
     * 当前工程的路径为 /Users/kmj/WorkSpace/git/study/JavaSE/
     */
    @Test
    public void demo15() throws InterruptedException {
        HttpServer httpServer = vertx.createHttpServer();
        httpServer.requestHandler(req -> {
            log.info("receive request");
            String path = req.path();
            log.info("request path {}", path);
            HttpServerResponse resp = req.response();
            File file = new File(".");
            log.info("file path {}", file.getAbsolutePath());
            switch (path) {
                case "/" -> resp.sendFile("./index.html");
                case "/page.html" -> resp.sendFile("." + path);
                default -> resp.sendFile("./default.html");
            }
        });
        httpServer.listen(80, ar -> {
            if (ar.succeeded()) {
                log.info("http server success");
            }
        });
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
}