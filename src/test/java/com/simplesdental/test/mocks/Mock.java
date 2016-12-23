package com.simplesdental.test.mocks;

public class Mock {
	public MockEnum tipo;
	public String foo;
	public Integer foo_int;

	public Mock() {
	}

	public Mock(MockEnum tipo, String foo, Integer foo_int) {
		this.tipo = tipo;
		this.foo = foo;
		this.foo_int = foo_int;
	}
}
