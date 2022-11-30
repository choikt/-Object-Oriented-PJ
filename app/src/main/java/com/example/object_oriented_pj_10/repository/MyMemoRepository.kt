package com.example.object_oriented_pj_10.repository

import android.util.Log
import com.example.object_oriented_pj_10.MemoList
import kotlinx.coroutines.delay
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class MyMemoRepository {

    var newList:ArrayList<MemoList> = ArrayList() ;

    fun postMemo(title: String, date: String, description: String, ) {
        var th: Thread = object : Thread() {
            override fun run() {
                super.run()
                val jsonOb = JSONObject()
                jsonOb.put("date", date)
                jsonOb.put("description", description)
                jsonOb.put("name", title)

                val url = URL("http://10.0.2.2:8080/diary") //http://10.0.2.2:8080
                var conn: HttpURLConnection? = null
                conn = url.openConnection() as HttpURLConnection
                conn.doOutput = true
                conn.requestMethod = "POST"
                conn.setRequestProperty("Connection", "Keep-Alive")
                conn.setRequestProperty("Content-Type", "application/json")

                val jsonStr = jsonOb.toString()
                val os: OutputStream = conn.getOutputStream()
                os.write(jsonStr.toByteArray(charset("UTF-8")))
                os.flush()

                val sb = StringBuilder()
                val HttpResult = conn.getResponseCode()
                if (HttpResult == HttpURLConnection.HTTP_OK) {
                    val br = BufferedReader(
                        InputStreamReader(conn.getInputStream(), "utf-8")
                    )

                    br.close()
                    println("" + sb.toString())
                } else
                    System.out.println(conn.getResponseMessage())
                os.close()
                Log.d("json", "" + jsonStr)

            }
        }
        th.start()
    }

    suspend fun getMemos(): ArrayList<MemoList> {
        var output = ""
        thread(start = true) {
            val url = URL("http://10.0.2.2:8080/diary")

            val huc = url.openConnection() as HttpURLConnection
            huc.requestMethod = "GET"

            if (huc.responseCode == HttpURLConnection.HTTP_OK) {
                val streamReader = InputStreamReader(huc.inputStream)
                val buffered = BufferedReader(streamReader)

                val content = StringBuilder()
                while (true) {
                    val data = buffered.readLine() ?: break
                    content.append(data)
                }

                buffered.close()
                huc.disconnect()

//                Log.d("결과값", "${content}")
                // 2. JSONArray 로 파싱
                val jsonArray = JSONArray("${content}")
//                Log.d("jsonArray", jsonArray.toString())


                // 3. JSONArray 순회: 인덱스별 JsonObject 취득후, key에 해당하는 value 확인
                for (index in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(index)

                    val title = jsonObject.getString("name")
                    val date = jsonObject.getString("date")
                    val description = jsonObject.getString("description")

//                    Log.d("jsonObject", jsonObject.toString())
//                    Log.d("json_id_language", "$title $date $description");
                    var memoList = MemoList(title, date, description)
                    newList.add(memoList);
                    Log.d("여기야!!", newList.toString())

                }
            }
        }
//        delay(3000);
        Log.d("여기야!!", newList.toString())
        return newList;
    }
}