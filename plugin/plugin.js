var exec = require('cordova/exec');
var UPPayPlugin = {};
UPPayPlugin.call = function (callName, params, success, error) {
    exec(success, error, 'UPPayPlugin', callName, params);
};
module.exports = UPPayPlugin;