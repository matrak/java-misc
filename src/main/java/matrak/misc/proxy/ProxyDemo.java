package matrak.misc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyDemo {
	
	public static void main(String[] args) {
		
		final FooBar foobar = new FooBar();
		InvocationHandler handler = new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				if(method.getName().equals("doSomething")) {
					return "Dynamic Proxy for Foobar";
				}
				else {
					return method.invoke(foobar, args);
				}
			}
		};
		
		Baz f = (Baz) Proxy.newProxyInstance(FooBar.class.getClassLoader(), new Class[] { Baz.class }, handler);
		System.out.println(f.doSomething());
	}
	
}
