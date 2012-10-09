<?php
/**
 * Look Busy
 *
 * Mocks a Phing build for each directory in this project's parent directory, then loops
 *
 * PHP Version 5.3
 *
 * @package    look-busy
 * @author     Doug Hurst <dalan.hurst@gmail.com>
 * @requires   pcntl
 */

declare(ticks = 1);
date_default_timezone_set('America/Chicago');
pcntl_signal(SIGINT, 'handleSignal');

function walk($directory, Closure $callback) {
    foreach (array_filter(scandir($directory), function($f) { return $f != '.' && $f != '..'; }) as $file) {
        if (is_dir($directory.DIRECTORY_SEPARATOR.$file)) {
            walk($directory.DIRECTORY_SEPARATOR.$file, $callback);
        } else {
            $callback($file);
        }
    }
};

function makeRenderer(array $renderers) {
    return function($file) use ($renderers) {
        echo "\033["
            .$renderers[rand(0, count($renderers) - 1)]($file)
            ."\033[m";
    };
}

function handleSignal($signal) {
    echo PHP_EOL."\033[0;31m".'Halting build!'.PHP_EOL."\033[m";
    sleep(1);
    echo "\033[0;33m".'Unwinding stack...'.PHP_EOL."\033[m";
    sleep(1);
    echo "\033[0;37m".'Build halted at '.rand(1,99).'%.'.PHP_EOL."\033[m".PHP_EOL;
    exit(1);
}

while (true) {
    walk(
        realpath(__DIR__.DIRECTORY_SEPARATOR.'..'),
        makeRenderer(array(
            function($filename) {
                return "0;37m".'phing> ['.date('r').'] Building include() files for '.$filename.PHP_EOL;
            },
            function($filename) {
                return "0;31m".'PHP ERROR: parse(): cannot parse '.$filename.PHP_EOL;
            },
            function($filename) {
                return "0;37m".'phing> ['.date('r').'] Generating collation from '.$filename.PHP_EOL;
            },
            function($filename) {
                return "0;37m".'phing> ['.date('r').'] Found '.rand(2, 9).' tasks in "'.$filename.'"'.PHP_EOL;
            },
            function($filename) {
                return "0;33m".'WARNING: file '.$filename.' contains deprecated flags'.PHP_EOL;
            },
            function($filename) {
                echo "\033[0;37m".'phing> ['.date('r').'] Waiting for response from server (ftp.'
                    .current(explode('.', $filename))
                    .'.com)...'
                    .PHP_EOL;
                sleep(rand(1, 3));
                return '';
            },
            function($filename) {
                echo "\033[0;37m".'phing> ['.date('r').'] Collecting entropy...'.PHP_EOL."\033[m";
                do {
                    sleep(rand(1, 2));
                    echo "\033[0;36m"
                        .array_reduce(
                            unpack('H*', file_get_contents('/dev/urandom', false, null, 0, 40)),
                            function($i, $x) { return $i.$x; },
                            ' >> '
                        )
                        .PHP_EOL
                        ."\033[m";
                } while(rand(1, 3) != 1);
                return '';
            }
        )
    ));
}
