package com.gamooga.storage3;

import static org.iq80.leveldb.impl.Iq80DBFactory.factory;

import java.io.File;
import java.io.IOException;

import org.iq80.leveldb.DB;
import org.iq80.leveldb.Options;

public class LevelDBStore {
	private LevelDBStore() {
	}

	private static DB instance;

	public static String put(String id) {
		if (instance == null) {
			DB levelDBStore;
			Options options = new Options();
			try {
				levelDBStore = factory.open(new File("levelDBStore3"), options);
				instance = levelDBStore;
			} catch (IOException e) {
				e.printStackTrace();
				return "Storing failed in storage3";
			}
		}
		String key = id.split("-")[0];
		String url = id.split("-")[1];
		instance.put(key.getBytes(), url.getBytes());
		return "Storing Success in storage3";
	}

	public static String get(String key) {
		if (instance == null)
			return "Key not found";
		return new String(instance.get(key.getBytes()));
	}
}
