/**
 * 1、需要能够遍历被访问者。
 * 2、通过访问者接口来抽象所有的被访问者动作
 * 3、不同的动作抽象不同的被访问者
 * 访问者模式
 *
 * 对于一个大的数据结构，不同层面的数据处理者需要关注不同的数据。
 * 于是将大的数据结构分成不同层面的数据，然后抽象出不同的数据处理者，将数据处理者和数据层面相关联起来。
 *
 * 表示一个作用于某对象结构中的各个元素的操作。访问者模式让用户可以在不改变各元素的类的前提下定义作用于这些元素的新操作。
 *
 * 应用场景有：
 * xml文档解析、编译器设计等
 *
 * @see java.nio.file.FileVisitor
 * @see javax.lang.model.element.AnnotationValueVisitor
 * @see javax.lang.model.element.AnnotationValue
 * @see javax.lang.model.element.Element
 * @see javax.lang.model.element.ElementVisitor
 *
 */
package design.pattern.behave.visitor;