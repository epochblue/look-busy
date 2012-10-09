/**
 * look-busy for JavaScript
 * Modified from Taroko Gorge (http://nickm.com/poems/taroko_gorge.html)
 * Designed for node.js
 * Author: Doug Hurst <dalan.hurst@gmail.com>
 * Date: 10/9/12
 */

var objects = 'authority,person,reference,institution,document,file'.split(',');
var properties = 'timestamp,name,location,email,phone,children,parent'.split(',');
var verbs = 'fetch,update,collect,send,read,write,track,annotate,seal,create,destroy'.split(',');

function rand(min, max) {
    var r = Math.floor(Math.random() * (max + min));
    return min > r ? min : r;
}

function choose(arr) {
    return arr[rand(1, arr.length - 1)];
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
    return JSON.stringify(o);
}

function command() {
    return '(function() { ' + choose(verbs) + ucfirst(choose(objects)) + '.with('
        + objects.slice(rand(0, 2), rand(2, (objects.length - 1))) + '); })()';
}

function colorln(line, fg) {
    console.log('\033[0;' + fg + 'm' + line + '\033[');
}

var n = 0;
var paths = 0;
setInterval(
    function() {
        if (n === 0) {
            colorln('', 37);
        } else if (n == 1) {
            colorln(combine(), 37);
            paths = rand(3, 4);
        } else if (n < paths) {
            colorln(define(), 37);
        } else if (n == paths) {
            colorln(combine(), 37);
        } else if (n == paths + 1) {
            colorln('', 37);
        } else if (n == paths + 2) {
            colorln(command(), 33);
        } else {
            colorln('', 37);
            n = 0;
        }
        n += 1;
    },
    1200
);

process.on('SIGINT', function() { colorln('', 37); process.exit(0); });
