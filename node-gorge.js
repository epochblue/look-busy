/**
 * look-busy for JavaScript
 * Modified from Taroko Gorge (http://nickm.com/poems/taroko_gorge.html)
 * Designed for node.js
 * Author: Doug Hurst <dalan.hurst@gmail.com>
 * Date: 10/9/12
 */

var n = 0;
var paths = 0;
var objects = 'building,street,person,office,reference,visit'.split(',');
var properties = 'timestamp,name,location,email,phone'.split(',');
var verbs = 'fetch,update,collect,send,read,write'.split(',');

function rand(min, max) {
    return Math.floor(Math.random() * (max + min));
}

function choose(array) {
    return array[rand(1, array.length - 1)];
}

function ucfirst(str) {
    return str.charAt(0).toUpperCase() + str.slice(1);
}

function combine() {
    var words = choose(objects);
    if (words == objects[rand(1, (objects.length - 1))]) {
        words = 'new ' + ucfirst(words) + '()';
    } else {
        words += '.' + choose(verbs) + '(' + choose(objects) + '.' + choose(properties) + ')';
    }
    return words;
}

function define() {
    var o = {};
    do {
        o[choose(properties)] = 'ref#' + rand(1001,9999);
    } while (rand(1, 2) == 1);
    return o;
}

function command() {
    return '(function() { ' + choose(verbs) + ucfirst(choose(objects)) + '.with('
        + objects.slice(rand(0, 2), rand(2, (objects.length - 1))) + '); })()';
}

function do_line() {
    if (n === 0) {
        console.log('');
    } else if (n == 1) {
        paths = 2 + rand(1, 2);
        console.log(combine());
    } else if (n < paths) {
        console.log(define());
    } else if (n == paths) {
        console.log(combine());
    } else if (n == paths + 1) {
        console.log('');
    } else if (n == paths + 2) {
        console.log(command());
    } else {
        console.log('');
        n = 0;
    }
    n += 1;
}

setInterval(do_line, 1200);
