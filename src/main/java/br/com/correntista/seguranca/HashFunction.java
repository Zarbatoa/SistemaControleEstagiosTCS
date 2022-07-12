package br.com.correntista.seguranca;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashFunction {

	public static String sha256(String senha) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(StandardCharsets.UTF_8.encode(senha));
			return String.format("%032x", new BigInteger(1, md.digest()));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Erro ao aplicar a função de hash SHA-256 na senha " + e.getMessage());
		}
		return null;
	}
}
