class MyReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
        Log.d("app","MyReceiver...............")
        val manager = context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
        val builder: NotificationCompat.Builder

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        //26버전 이상
        val channelId = "one-channel"
        val channelName = "My Channel One"
        val channel = NotificationChannel(
        channelId,
        channelName,
        NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
        //채널에 다양한 정보 설정
        description = "My Channel One Description"
        setShowBadge(true)
        val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val audioAttributes = AudioAttributes.Builder()
        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
        .setUsage(AudioAttributes.USAGE_ALARM)
        .build()
        setSound(uri, audioAttributes)
        enableVibration(true)
        }

        //채널을 NotificationManager에 등록
        manager.createNotificationChannel(channel)

        //채널을 이용하여 builder 생성
        builder = NotificationCompat.Builder(context, channelId)
        } else {
        //26버전 이하
        builder = NotificationCompat.Builder(context)
        }

        builder.run {
        //알림의 기본 정보
        setSmallIcon(android.R.drawable.ic_notification_overlay)
        setWhen(System.currentTimeMillis())
        setContentTitle("홍길동")
        setContentText("안녕하세요")
        }
        manager.notify(11, builder.build())
        }
        }