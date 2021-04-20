// function.js
function sum(num3, num4) {
    var num1 = 10;
    var num2 = 20;
    console.log(num1 + num2 + num3 + num4); // 없는 변수들이 있어도 자바스크립트는 오류를 안 낸다.
    // 있으면 있는대로 없으면 없는대로 있는 값으로 계산함.
    return num1 + num2 + num3 + num4;
}

//var result = sum(30, 40, 50);
var numAry = [3, 5, 1, 8, 9];

function arySum(ary) {
    var sum = 0;

    for (var i = 0; i < ary.length; i++) {

        if (i % 2 == 1) {
            sum += ary[i];
        }

    }
    return sum;
}

var result = arySum(numAry);
document.write('결과 값은 : ' + result);

function multi(grade) {
    var tb1 = '<table border = "1">';
    for (var a = 1; a < 10; a++) {
        tb1 += '<tr><td>'+grade+' * ' + a + '&nbsp;&nbsp;</td><td>&nbsp;&nbsp;=&nbsp;&nbsp;</td><td>&nbsp;&nbsp;' +
            (grade * a) + '&nbsp;&nbsp;</td></tr>';
    }
    tb1 += '</table>'
    document.write(tb1);
}
multi(8);

function multi2() {
    document.write('<p/>');
    document.write('&lt;구구단 출력하기&gt;');

    for (var i = 2; i <= 9; i++) {
        var tableTag = '<table border = "1">';
        for (var j = 1; j <= 9; j++) {
            tableTag += '<tr><td>' + i + ' * ' + j + '</td><td>&nbsp;=&nbsp;</td><td>' + (i * j) + '</td></tr>';
        }
        tableTag += '</table>'
        document.write('<br>' + tableTag);
    }
}
multi2();
