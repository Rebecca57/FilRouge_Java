package fr.m2i.singleton;

import com.nimbusds.jose.jwk.ECKey;

public final class PrivateKey {
	
	private static PrivateKey INSTANCE;
	private  ECKey key=null;
	
	public static PrivateKey getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new PrivateKey();
		}
		return INSTANCE;
	}

	public static PrivateKey getINSTANCE() {
		return INSTANCE;
	}

	public static void setINSTANCE(PrivateKey iNSTANCE) {
		INSTANCE = iNSTANCE;
	}

	public ECKey getKey() {
		return key;
	}

	public void setKey(ECKey key) {
		this.key = key;
	}


}
