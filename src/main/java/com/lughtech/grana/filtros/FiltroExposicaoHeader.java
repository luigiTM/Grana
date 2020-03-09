package com.lughtech.grana.filtros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class FiltroExposicaoHeader implements Filter {

	@Override
	public void doFilter(ServletRequest requisicao, ServletResponse resposta, FilterChain cadeiaFiltro) throws IOException, ServletException {
		HttpServletResponse respostaHttp = (HttpServletResponse) resposta;
		respostaHttp.addHeader("acces-control-expose-header", "location");
		cadeiaFiltro.doFilter(requisicao, resposta);
	}

}
