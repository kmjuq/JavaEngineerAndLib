package spock

import org.mockito.MockedStatic
import org.mockito.Mockito
import spock.lang.*
import spock.util.environment.Jvm
import spock.util.environment.OperatingSystem

import static org.hamcrest.Matchers.hasItem
import static org.hamcrest.Matchers.not

/**
 * 当有继承测试时，执行顺序如下：
 * super.setupSpec
 * sub.setupSpec
 * super.setup
 * sub.setup
 * feature method 你写的测试方法
 * sub.cleanup
 * super.cleanup
 * sub.cleanupSpec
 * super.cleanupSpec
 *
 **/
// 表明被测试主体，当通过文件名无法标识出测试主体时使用
@Subject(SpockSpecification)
// 简介说明测试类
@Title("用来展示Spock样例的范本")
// 类的大段简介说明
@Narrative("""
大段简介说明
""")
// 用来关联issue管理工具的，比如jira，禅道等。只是语义话文本并没有实质关联
@Issue("https://ip:port/issue/xxx")
// 按编写顺序执行测试代码，在测试有依赖关系的测试中使用
@Stepwise
class SpockSpecification extends Specification {
    // 测试类 内容规范
    // fields
    // fixture methods
    // feature methods
    // helper methods

    /**
     * fields
     */
//    // 标识是该变量的生命周期是整个测试类
//    @Shared
//            node = KNode.of(0)

    /**
     * fixture methods
     */
    def setup() {
        println("每个测试方法都会执行setup一次")
    }

    def cleanup() {
        println("每个测试方法都会执行cleanup一次")
    }

    /**
     * setupSpec 和 cleanupSpec 方法只能访问静态字段和标注@Shared的对象
     */
    def setupSpec() {
        println("一个测试类只会执行setupSpec一次")
    }

    def cleanupSpec() {
        println("一个测试类只会执行cleanupSpec一次")
    }

    /**
     * feature methods
     */
    def "五个spock语义块"() {
        given: "初始化"
        when: "调用测试方法"
        then: "检验测试结果"
        cleanup: "释放资源"
        where: "参数化测试"
    }

    /**
     * 理论上and语义块可以用在很多地方，但是建议只用在given
     */
    def "and语义块，对其他语义块的补充，使内容划分更加整洁"() {
        given: "准备数据"
        and: "准备数据1"
        and: "准备数据2"
        when: "调用测试方法"
        then: "测试结果断言"
    }

    def "given when then语义流程"() {
        given:
        def a = 2
        def b = 3

        when:
        def result = Math.max(a, b)

        then:
        result == 3
    }

    /**
     * 适用于when和then比较简单的场景
     */
    def "expect语义块，相当于 when + then"() {
        expect:
        Math.max(2, 3) == 3
    }

    def "cleanup语义块，用来清理测试方法产生的资源"() {
        given:
        def file = new File("path.log")
        file.createNewFile()

        cleanup:
        file.delete()
    }

    def "where语义块，参数化测试"() {
        expect:
        Math.max(a, b) == c

        where:
        a | b || c
        0 | 9 || 9
        4 | 2 || 4
    }

    def "抛出异常的方法"() {
        given:
        def stack = new Stack()

        when:
        stack.pop()

        then:
        // 当方法抛出异常时，thrown 用来校验抛出的异常
        thrown(EmptyStackException)
        stack.empty
    }

    def "交互测试"() {
//        given:
//        // 必须使用Mock才能计数
//        def repo1 = Mock(Repository)
//        def repo2 = Mock(Repository)
//        def service = new Service(repositorys: [
//                repo1,
//                repo2,
//        ])
//
//        when:
//        service.calls()
//
//        then:
//        1 * repo1.call(1)
//        1 * repo2.call(1)
//
//        when:
//        service.calls()
//
//        then:
//        (1..3) * repo1.call(*_)
//        (1.._) * repo2.call(*_)
//
//        when:
//        service.calls()
//
//        then:
//        (_..3) * repo1.call(1)
//        _ * repo1.call(1)
    }

    def "with方法方便校验对象属性"() {
//        given:
//        def node = KNode.of(1)
//
//        when:
//        node.value()
//
//        then:
//        with(node) {
//            prev == null
//            next == null
//            value == 1
//            value() == 1
//        }
    }

    def "with方法校验方法执行次数"() {
//        given:
//        def node = Mock(KNode)
//
//        when:
//        node.value()
//
//        then:
//        with(node) {
//            1 * value()
//        }
    }

    /**
     * 方法可通过 #n 方式获取变量当模板，在测试报告中展现。
     */
    def "方法名称模板：max(#a,#b)=#n"() {
//        expect:
//        Math.max(a, b) == n
//
//        where:
//        a | b || n
//        2 | 6 || 6
//        5 | 7 || 7
    }

    /**
     * {@link Rollup} 将where参数化测试的feature methods或者使用方法名模板的feature methods聚合为一个方法报告
     * {@link Unroll} 默认是 Unroll
     */
    @Rollup
    def "Feature @Rollup"() {
//        expect:
//        Math.max(a, b) == n
//
//        where:
//        a | b || n
//        2 | 6 || 6
//        5 | 7 || 7
    }

    /**
     * {@link spock.mock.MockingApi#Stub()} 模拟方法返回的内容。主要是为测试提供预先设定好的响应，而不关注方法调用的细节。
     * {@link spock.mock.MockingApi#Mock()} Mock是Stub的超集，能用Stub的地方就能使用Mock，Mock 可以判断方法执行次数。检查被测试代码是否按照预期调用了这些方法，包括调用的次数、顺序以及传入的参数等。
     * {@link spock.mock.MockingApi#Spy()} 既可以执行真实对象，也可以模拟，特殊场景才使用。
     */
    def "stub method"() {
//        given:
//        Repository repo1 = Stub()
//        Repository repo2 = Stub()
//        Service service = new Service(repositorys: [repo1,repo2])
//
//        when:
//        repo1.call(_) >> 1
//        repo2.call(_) >> 2
//
//        then:
//        service.sum() == 6
    }

    def "mock method"() {
//        given:
//        Repository repo = Mock()
//        Service service = new Service(repositorys: [repo,repo])
//
//        when:
//        service.calls()
//
//        then:
//        2 * repo.call(_)
    }

    /**
     * 只能用于groovy的静态方法
     */
    def "groovy static method"() {
//        given:
//        Service service = GroovySpy(global: true)
//
//        expect:
//        Service.add(a, b, c) == d
//
//        where:
//        a | b | c || d
//        1 | 2 | 3 || 6
    }

    def "subclass invoke superclass method"(){
        given:
        Service service = new Service()
        Service serviceSpy = Spy(service)

        when:
        serviceSpy.show() >> _
        serviceSpy.testSuperShow()
        serviceSpy.testShow()

        then:
        noExceptionThrown()
    }

    def "static method by Mockito"(){
        given:
        MockedStatic<Component> component = Mockito.mockStatic(Component)
        Service serviceSpy = Spy(Service)

        when:
        component.when { Component.line(4) }.thenReturn(100)
        serviceSpy.testline()

        then:
        noExceptionThrown()
    }

    /**
     * extra 额外扩展
     */

    def "Hamcrest"() {
        given: "a list of products"
        def products = ["camera","laptop","hifi"]

        expect: "camera should be one of them"
        products hasItem("camera")

        and: "hotdog is not one of them"
        products not(hasItem("hotdog"))
    }

    def "sql"(){

    }

    def "springboot"() {

    }

    def "rest client"(){

    }

    /**
     * 现在前端项目高度动态化，很难进行前端的自动化测试
     */
    def "UI"() {

    }

    /**
     * {@link Ignore} 用于类时，使类的所有测试跳过；用于方法时，使该方法跳过。
     * {@link IgnoreRest} 只能作用于方法，只执行该方法，其他方法不执行
     * {@link IgnoreIf} 用法同@Ignore，但是需要条件
     * {@link Requires} 用法和 @IgnoreIf 相反
     *
     * 使用场景有新版本功能但不需上线时，通过此类注解可以忽视掉自动化测试
     */
    //@Ignore
    //@IgnoreRest
    @IgnoreIf({
        Jvm.getCurrent().isJava11Compatible()
    })
    def "@IgnoreIf"() {
        expect:
        Math.max(2, 3) == 3
    }

    @Requires({
        def os = OperatingSystem.getCurrent()
        os.isMacOs()
    })
    def "@Requires"() {
        expect:
        Math.max(2, 3) == 3
    }

    /**
     * helper methods
     */

}
