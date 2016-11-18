package com.simplesdental;

import com.simplesdental.helpers.request.RequestError;
import com.simplesdental.models.Assinatura;
import com.simplesdental.models.AssinaturaResultado;
import com.simplesdental.resources.Assinar;

/**
 * PagueVeloz pakage for integration with pagueveloz.com
 */
public class PagueVeloz {
	public static void main(String[] args) {
		Assinatura assinaturaDtoV4 = new Assinatura();

		try {
			AssinaturaResultado create = Assinar.create(assinaturaDtoV4);
		} catch (RequestError e) {
			System.out.println(e.getMessage());
		}
	}
}
