/**
 * variable.js
 */

		// javascript 문법
		document.write("<h1 style = 'background-color: yellow;'>Hello World!</h1>");
		console.log('Hello World');
		
		// 변수를 만들어서 내용을 담은 뒤 출력할 수 있음
		var ulTag = "<ul><li>Apple</li><li>Banana</li><li>Cherry</li></ul>";
		document.write(ulTag);

		ulTag = 10;
		ulTag = true;
		ulTag = null;
		ulTag = 10 + 20;
		console.log(typeof ulTag);	// ulTag에 들어있는 자료형에 따라 String인지 Number인지 확인 가능
									// 변수 선언 시 자료형을 미리 지정해서 선언하는 것이 아니라,
									// 변수에 들어가있는 값에 따라 변수의 자료형이 달라진다.