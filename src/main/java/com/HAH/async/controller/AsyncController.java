package com.HAH.async.controller;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.async.DeferredResult;

@Controller
@RequestMapping("async")
public class AsyncController {

	@GetMapping("callable")
	public Callable<String> callableExecution(ModelMap model) {
		return new Callable<String>() {

			@Override
			public String call() throws Exception {
				model.put("message", "Hello Message From Callable Result.");
				return "async-result";
			}
		};
	}

	@GetMapping("deferred")
	public DeferredResult<String> deferredResult(@RequestParam(required = false) String error, ModelMap model) {
		var result = new DeferredResult<String>(1000L, "async-result");
		model.put("message", "Hello Message From Deferred Result.");

		Executors.newSingleThreadExecutor().execute(() -> {
			try {
				if (null != error) {
					model.addAttribute("message", "Error Message : %s".formatted(error));
					result.setErrorResult("error-result");
				}

				Thread.sleep(1000);

				result.setResult("async-result");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		return result;
	}

}
