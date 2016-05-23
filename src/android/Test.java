package com.example.plugin;

import com.me.android.kaunter.object.Pembayar;
import com.me.android.service.CetakTiket;
import com.me.android.service.NoToWord;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class Test extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

        if (action.equals("func1")) {
            func1(data.getJSONObject(0), callbackContext);
            return true;
        } else if (action.equals("func2")) {
            func2(data.getJSONObject(0), callbackContext);
            return true;
        } else {
            return false;
        }
    }

    private void func2(JSONObject data, CallbackContext callbackContext) {
        try {
            int resitNo = data.getInt("resitNo");
            CetakTiket.cetakTiket(resitNo);
        } catch (Exception e) {

        }
    }
    private void func1(JSONObject data, CallbackContext callbackContext) {
        try {
            Pembayar pembayar=new Pembayar();
            int resitNo = data.getInt("resitNo");

            pembayar.setNoResit(resitNo);
            Float harga1 = new Float(0.00);
            Float harga2 = new Float(0.00);
            Float harga3 = new Float(0.00);

            String inpJabatan = data.getString("inpJabatan");
            String inpTempat = data.getString("inpTempat");
            String inpNama = data.getString("inpNama");

            if (inpJabatan.length() > 0)
                pembayar.setJabatan(inpJabatan);
            if (inpTempat.length() > 0)
                pembayar.setTempat(inpTempat);
            if (inpNama.length() > 0)
                pembayar.setNama(inpNama);

            if (data.has("item1")) {
                String inpItem = data.getJSONObject("item1").getString("inp");
                String value = data.getJSONObject("item1").getString("value");
                harga1 = Float.valueOf(value);
                if (inpItem.length() > 0 && value.length() > 0) {
                    pembayar.setItem1(inpItem);
                    pembayar.setHarga1(value);
                }
            }

            if (data.has("item2")) {
                String inpItem = data.getJSONObject("item1").getString("inp");
                String value = data.getJSONObject("item1").getString("value");
                harga2 = Float.valueOf(value);
                if (inpItem.length() > 0 && value.length() > 0) {
                    pembayar.setItem1(inpItem);
                    pembayar.setHarga2(value);
                }
            }

            if (data.has("item3")) {
                String inpItem = data.getJSONObject("item1").getString("inp");
                String value = data.getJSONObject("item1").getString("value");
                harga3 = Float.valueOf(value);
                if (inpItem.length() > 0 && value.length() > 0) {
                    pembayar.setItem1(inpItem);
                    pembayar.setHarga3(value);
                }
            }

            DecimalFormat df = new DecimalFormat("#,###.00");
            Float totalHarga = (harga1 + harga2 + harga3);
            String hargaTotal = new String(df.format(totalHarga));
            String[]splitHarga = hargaTotal.split("\\.");
            NoToWord obj = new NoToWord();
            String rmDesc = obj.convert(Integer.valueOf(splitHarga[0]).intValue());
            String SenDesc = obj.convert(Integer.valueOf(splitHarga[1]).intValue());
            pembayar.setTotalHarga(totalHarga);
            pembayar.setRmDesc(rmDesc);
            pembayar.setSenDesc(SenDesc);
            CetakTiket.cetakKaunter(pembayar);

        } catch (Exception e) {

        }
    }
}
