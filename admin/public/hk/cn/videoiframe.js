$(function () {
    let oPlugin = {
        iWidth: 200,            // plugin width
        iHeight: 163            // plugin height
    };
    WebVideoCtrl.I_InitPlugin(oPlugin.iWidth, oPlugin.iHeight, {
        bWndFull: true,
        iWndowType: 1,
        cbInitPluginComplete: function () {
            WebVideoCtrl.I_InsertOBJECTPlugin("divPlugin");
        }
    });
});