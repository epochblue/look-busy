<?php
/**
 * Look Busy
 *
 * Mocks a Phing build for each directory in this file's parent directory, then loops
 *
 * PHP Version 5.3
 *
 * @package    look-busy
 * @author     Doug Hurst <dalan.hurst@gmail.com>
 */

date_default_timezone_set('America/Chicago');

$walker = function($directory, array $renderers) use (&$walker) {
    array_walk(
        scandir($directory),
        function($file) use ($directory, $walker, $renderers) {
            if (is_dir($directory.DIRECTORY_SEPARATOR.$file) && $file[0] != '.') {
                $walker($directory.DIRECTORY_SEPARATOR.$file, $renderers);
            } else {
                echo $renderers[rand(0, count($renderers) - 1)]($file);
            }
        }
    );
};

while (true) {
    $walker(
        realpath(__DIR__.DIRECTORY_SEPARATOR.'..'),
        array(
            function($filename) {
                return 'phing> ['.date('r').'] '
                    .($filename[0] == '.'
                        ? 'Refusing to build include() for '.$filename
                        : 'Building include() files for '.$filename)
                    .PHP_EOL;
            },
            function($filename) {
                return 'PHP ERROR: parse(): cannot parse '.$filename.PHP_EOL;
            },
            function($filename) {
                return 'phing> ['.date('r').'] Collating '.$filename.PHP_EOL;
            },
            function($filename) {
                return 'phing> ['.date('r').'] Found '.rand(1, 9).' tasks in "'.$filename.'"'.PHP_EOL;
            },
            function($filename) {
                return 'WARNING: file '.$filename.' contains deprecated flags'.PHP_EOL;
            },
            function($filename) {
                echo 'phing> ['.date('r').'] Waiting for response from server...'.PHP_EOL;
                sleep(rand(1, 3));
                return '';
            }
        )
    );
}
