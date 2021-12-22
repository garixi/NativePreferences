var exec = require('cordova/exec');

exports.coolMethod = function (arg0, success, error) {
    exec(success, error, 'NativePreferences', 'coolMethod', [arg0]);
};

exports.read = function (key, success, error) {
    exec(success, error, 'NativePreferences', 'read', [key]);
};

exports.setValue = function (key, success, error) {
    exec(success, error, 'NativePreferences', 'setValue', [key]);
};

exports.removeKey = function (key, success, error) {
    exec(success, error, 'NativePreferences', 'removeKey', [key]);
};
