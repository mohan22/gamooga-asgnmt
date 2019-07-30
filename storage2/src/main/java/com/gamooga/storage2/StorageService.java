package com.gamooga.storage2;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bitly2")
public class StorageService {
	@RequestMapping("/keys/{id}")
	public String save(@PathVariable String id) {
		System.out.println("storage 2 received: " + id);
		return LevelDBStore.put(id);

	}

	@RequestMapping("/url/{id}")
	public String getUrl(@PathVariable String id) {
		System.out.println("storage 2 url request: " + id);
		return LevelDBStore.get(id);
	}

}
