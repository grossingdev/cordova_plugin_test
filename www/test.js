/*global cordova, module*/

module.exports = {
    test: function (param, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "Test", "greet", param);
    }
};
