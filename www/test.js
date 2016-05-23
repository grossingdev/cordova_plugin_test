/*global cordova, module*/

module.exports = {
    func1: function (param, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "Test", "func1", param);
    },
    func2: function (param, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "Test", "func2", param);
    }
};
