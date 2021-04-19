// function3.js

var sum = function(a, b) {  // 뭐 이렇게도 쓸 수 있다.....
    var num1 = 10;
    var num2 = 20;
    console.log(a + num1 + num2 + b);
}
sum(' 결과는 ', '입니다.');
console.log(sum);

var factorial = function fac(n) {
    if(n == 1){
        return 1;
    }
    return fac(n - 1);
}

var result = factorial(3);
console.log(result);