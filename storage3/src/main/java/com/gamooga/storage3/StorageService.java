package com.gamooga.storage3;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bitly3")
public class StorageService {
	@RequestMapping("/keys/{id}")
	public String save(@PathVariable String id) {
		System.out.println("storage 3 received: " + id);
		return LevelDBStore.put(id);

	}

	@RequestMapping("/url/{id}")
	public String getUrl(@PathVariable String id) {
		System.out.println("storage 3 url request: " + id);
		return LevelDBStore.get(id);
	}

}
