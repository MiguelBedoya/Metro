package com.personal.postgresql.services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.personal.postgresql.constants.ConstantesRespuestas;
import com.personal.postgresql.dto.QrDTO;
import com.personal.postgresql.dto.UsuarioDTO;
import com.personal.postgresql.entities.Qr;
import com.personal.postgresql.entities.Usuario;
import com.personal.postgresql.mappers.QrMapper;
import com.personal.postgresql.mappers.UsuarioMapper;
import com.personal.postgresql.repositories.QrDao;
import com.personal.postgresql.repositories.UsuarioDao;
import com.personal.postgresql.responses.RespuestaGenerica;
import net.glxn.qrgen.javase.QRCode;

@Service
public class QrService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final QrMapper qrMapper = new QrMapper();	

	@Autowired
	private QrDao qrDao;

	public @ResponseBody ResponseEntity<RespuestaGenerica> crearYGenerateQRCodeImage()  {
		try {

			RespuestaGenerica respuestaGenerica;

			Qr qrAntesDeGuardado = new Qr();
			Qr qrGuardado = this.qrDao.save(qrAntesDeGuardado);
			
			ByteArrayOutputStream stream = QRCode.from(qrGuardado.getId() + "").withSize(250, 250).stream();
			ByteArrayInputStream bis = new ByteArrayInputStream(stream.toByteArray());
			
			byte[] bytes = IOUtils.toByteArray(bis);
			
			String stringCodeQR = "";//new sun.misc.BASE64Encoder().encode(bytes);
			
			stringCodeQR = stringCodeQR.replaceAll("(\\r|\\n)", "");
			
						
			qrGuardado.setCodigo("data:image/png;base64," + stringCodeQR);
			
			this.qrDao.save(qrGuardado);
			
			respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.EXITO, "data:image/png;base64," + stringCodeQR);

			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error se presentan problemas al cargar el código QR ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}

}
