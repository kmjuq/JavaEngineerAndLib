/**
 * 中介者模式主要是简化多对多关系的对象的通信模型。
 * 多对多关系的每一个对象会持有其他对象，假设有5个对象，每个对象就得持久4个其他对象才能全部进行通信。
 * 而中介者模式是每个对象持有中介者，然后中介者持有所有对象，对象之间的通信通过中介者来沟通。
 * <p>
 * 中介者模式
 */
package design.pattern.behave.mediator;