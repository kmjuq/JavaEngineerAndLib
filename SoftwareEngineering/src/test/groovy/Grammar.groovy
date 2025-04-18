import lombok.extern.slf4j.Slf4j

/**
 * groovy grammer
 */
@Slf4j
class Grammar {

    DIDo diDo;

    def groovyDoc() {
        assert DIDo.class.groovydoc.content.contains('Some class groovydoc for Foo');
        assert DIDo.class.getMethod('generatorMap', new Class[0]).groovydoc.content.contains('Some method groovydoc for bar');
        assert DIDo.class.getField("action").groovydoc.content.contains('Some field groovydoc for action')
    }
}

/**@
 * Some class groovydoc for Foo
 */
class DIDo {

    /**@
     * Some field groovydoc for action
     */
    String action;

    /**@
     * Some method groovydoc for bar
     *
     * @param kv kv值
     * @return map
     */
    def generatorMap(String kv) {
        ["Hello ${kv}": "Hello ${kv}"]
    }

    def mapcrud() {
        def person = [:]
        person."name" = 'kmj'

        def age = '59'
        person."${age} 岁" = 'age'

        println person
    }
}