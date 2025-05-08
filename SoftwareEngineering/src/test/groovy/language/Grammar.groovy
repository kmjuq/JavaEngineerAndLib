package language

import lombok.extern.slf4j.Slf4j

import java.util.regex.Matcher

/**
 * groovy grammer
 */
@Slf4j
class Grammar {

    static void main(String... args) {
        println 'Groovy world!'
//        groovyDoc()
        dataType()
        dataTypeForList()
        dateTypeForMap()
        operator()
        constructor()

    }

    static def groovyDoc() {
        assert Service.class.groovydoc.content.contains('Some class groovydoc for Foo')
        assert Service.class.getMethod('calls').groovydoc.content.contains('Some method groovydoc for bar')
        assert Service.class.getField("name").groovydoc.content.contains('Some field groovydoc for action')
    }


    static def dataType() {
        def s1 = "1"
        def s2 = "${s1} 2"
    }

    static def dataTypeForList() {
        // 默认是ArraysList
        def l1 = [1, 2, 3]
        // 使用 LinkedList
        def l2 = ["1", "2"] as LinkedList
        // 使用 LinkedList
        LinkedList otherLinked = [3, 4, 5]
        // 多重数组
        def multi = [[1, 2], [2, 3]]
        // 如果需要使用数组
        def arr = [1, 2, 3] as int[]
        // read
        l1[1]
        l1[0..2]
        // modify
        l1[2] = 4
        l1[0, 2] = [5, 4]
        l2[0..2] = [6, 5, 4]
        // add 将元素附加到列表末尾
        l1 << 4
    }

    static def dateTypeForMap() {
        // 默认是 LinkedHashMap
        def colors = [red: '#FF0000', green: '#00FF00', blue: '#0000FF']
        // add
        colors['pink'] = '#FF00FF'
        colors.yellow = '#FFFF00'
        // modify
        colors.red = '';
        // 使用动态的key
        def name = 'name'
        def person = ["${name}": 'kmj']
    }

    /*
    groovy 支持运算符重载


| 操作符 | 方法                    |
| :----- | :---------------------- |
| +      | a.plus(b)               |
| -      | a.minus(b)              |
| *      | a.multiply(b)           |
| /      | a.div(b)                |
| %      | a.mod(b)                |
| **     | a.power(b)              |
| \|     | a.or(b)                 |
| &      | a.and(b)                |
| ^      | a.xor(b)                |
| as     | a.asType(b)             |
| a()    | a.call(b)               |
| a[b]   | a.getAt(b)              |
| a[b]=c | a.putAt(b, c)           |
| a in b | b.isCase(a)             |
| <<     | a.leftShift(b)          |
| >>     | a.rightShift(b)         |
| >>>    | a.rightShiftUnsigned(b) |
| ++     | a.next(b)               |
| --     | a.previous(b)           |
| +a     | a.positive(b)           |
| -a     | a.negative(b)           |
| ~a     | a.bitwiseNegate(b)      |
     */

    static def operator() {
        // . 运算符调用会调用同名的getter setter方法
        def service = new Service()
        // ?. 如果 dido 为 null ，则 action1 为 null
        assert null === service?.name

        // elvis 运算符
        assert 'default' === service.name ?: 'default'
        service.name ?= 'do'
        assert 'do' === service.name

        // 直接访问字段，而不是通过 setter getter访问
        service.@name = 'do'

        // 方法指针运算符 实例方法指针
        def fun = 'kmj'.&replace
        assert 'kmm' == fun('j', 'm')

        // 通过类来获取实例方法指针 调用时需要传实例
        def instanceMethod = String.&toUpperCase
        assert 'FOO' == instanceMethod('foo')

        // 模式运算符 字符串里面的 $ 符号默认是 ${}
        def p = ~/foo/
        // $/ /$ 是两个定界符
        p = ~$/dollar/slashy $ string/$

        // 查找运算符
        assert ("dollar/slashy" =~ ~$/dollar/slashy/$) instanceof Matcher
        // 匹配运算符 当使用匹配运算符时，模式可以不用加 ~
        assert ("dollar/slashy" ==~ $/dollar/slashy/$) instanceof Boolean

        // 扩展运算符
        def didos = [
                new Service(name: 'do1', repositorys: [new Repository(name: 'repo11'), new Repository(name: 'repo12')]),
                new Service(name: 'do2', repositorys: [new Repository(name: 'repo21'), new Repository(name: 'repo22')])
        ]
        assert ['do1', 'do2'] == didos*.name
        assert [['repo11', 'repo12'], ['repo21', 'repo22']] == didos*.repositorys*.name

        // 扩展运算符
        def xyz = [1, 2, 3]
        assert 6 === Service.add(*xyz)
        // 将普通参数与扩展参数混合
        def xy = [2, 2]
        assert 6 === Service.add(*xy, 2)

        // 展开列表
        def items = [4, 5]
        assert [1, 2, 3, *items, 6] == [1, 2, 3, 4, 5, 6]
        // 展开 map , 重复的定义会覆盖之前的定义
        def m1 = [c: 3, d: 4]
        assert [a: 1, b: 2, *: m1, d: 8] == [a: 1, b: 2, c: 3, d: 8]

        // 范围运算符
        assert (0..5).collect() == [0, 1, 2, 3, 4, 5]
        assert (0..<5).collect() == [0, 1, 2, 3, 4]
        assert (0<..5).collect() == [1, 2, 3, 4, 5]
        assert (0<..<5).collect() == [1, 2, 3, 4]
        assert (0..5) instanceof List
        assert (0..5).size() == 6

        // 下标运算符 getAt putAt方法

        // 安全索引运算符
        def li1 = []
        def mi1 = [:]
        assert null == li1?[0]
        assert null == mi1?['map']

        // in 运算符
        assert ('k' in ['k', 'm', 'j'])
        assert ('z' !in ['k', 'm', 'j'])

        // 强制转换运算符 通过 asType 方法实现自定义转换规则

        // ()运算符
        def repo = new Repository(intValue: 1)
        assert repo.call(3) == 4
        assert repo(3) == 4
    }

    static def constructor() {
        def repo2 = new Repository(name: 'repo2')
        def repo3 = new Repository(name: 'repo3', intValue: 1)
    }
}

/**@
 * Some class groovydoc for Foo
 */
class Service {

    /**@
     * Some field groovydoc for action
     */
    public String name

    /**@
     * repository
     */
    public List<Repository> repositorys

    /**@
     * cache
     */
    public Map<String, String> cache

    /**@
     * 数字相加
     * @param x
     * @param y
     * @param z
     * @return
     */
    static int add(int x, int y, int z) {
        return x + y + z
    }

    /**@
     * 调用repo的call
     */
    def calls() {
        for (def repo in repositorys) {
            repo.call(1)
        }
    }

    /**
     *
     * @return
     */
    def sum() {
        return 3 + repositorys*.call(4).sum(0)
    }
}

class Repository {

    String name

    int intValue

    def call(int x) {
        return x + intValue
    }

}