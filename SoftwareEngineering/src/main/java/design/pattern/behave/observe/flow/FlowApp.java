package design.pattern.behave.observe.flow;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

@Slf4j
public class FlowApp {

    private static SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

    private static Flow.Subscriber<Integer> subscriber = new FlowSubscriber<>() {
        private Flow.Subscription subscription;

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = subscription;
            subscription.request(3);
        }

        @Override
        public void onNext(Integer item) {
            log.info("subscriber onnext {}", item);
            subscription.request(3);
        }
    };

    private static Flow.Processor<String, Integer> processor = new FlowProcessor<>() {
        private Flow.Subscription subscription;

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = subscription;
            subscription.request(1);
        }

        @Override
        public void onNext(String item) {
            log.info("processor onnext {}", item);
            submit(Integer.valueOf(item));
            subscription.request(1);
        }
    };

    private static Flow.Subscription subscription = new FlowSubscription() {

    };

    public static void main(String[] args) throws InterruptedException {
        publisher.subscribe(processor);
        processor.subscribe(subscriber);

        publisher.submit("1");
        publisher.submit("2");
        publisher.submit("3");
        Thread.currentThread().join();
    }

}
