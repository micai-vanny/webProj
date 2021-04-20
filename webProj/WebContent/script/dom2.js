/**
 * dom2.js
 */

		var formTag = document.createElement('form');
		formTag.setAttribute('action', 'login.jsp');
		formTag.setAttribute('method', 'get');
		
		var id = document.createTextNode('id : ');
		var password = document.createTextNode('password :');
		
		var inputId = document.createElement('input');
		inputId.setAttribute('type', 'text');
		inputId.setAttribute('name', 'id');
		
		var inputPw = document.createElement('input');
		inputPw.setAttribute('type', 'password');
		inputPw.setAttribute('name', 'passwd');
		
		var inputSub = document.createElement('input');
		inputSub.setAttribute('type', 'submit');
		inputSub.setAttribute('value', 'send');
		
		var inputReset = document.createElement('input');
		inputReset.setAttribute('type', 'reset');
		inputReset.setAttribute('value', 'cancel');
		
		formTag.appendChild(id);
		formTag.appendChild(inputId);
		formTag.appendChild(password);
		formTag.appendChild(inputPw);
		formTag.appendChild(inputSub);
		formTag.appendChild(inputReset);
		
		document.body.appendChild(formTag);