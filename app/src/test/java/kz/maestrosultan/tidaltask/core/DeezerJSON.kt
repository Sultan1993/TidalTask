package kz.maestrosultan.tidaltask.core

object DeezerJSON {

    val searchJSON = """
        [
                {
                  "id": "13",
                  "name": "Eminem",
                  "link": "https://www.deezer.com/artist/13",
                  "picture": "https://api.deezer.com/artist/13/image",
                  "picture_small": "https://cdns-images.dzcdn.net/images/artist/0707267475580b1b82f4da20a1b295c6/56x56-000000-80-0-0.jpg",
                  "picture_medium": "https://cdns-images.dzcdn.net/images/artist/0707267475580b1b82f4da20a1b295c6/250x250-000000-80-0-0.jpg",
                  "picture_big": "https://cdns-images.dzcdn.net/images/artist/0707267475580b1b82f4da20a1b295c6/500x500-000000-80-0-0.jpg",
                  "picture_xl": "https://cdns-images.dzcdn.net/images/artist/0707267475580b1b82f4da20a1b295c6/1000x1000-000000-80-0-0.jpg",
                  "nb_album": 52,
                  "nb_fan": 13340025,
                  "radio": true,
                  "tracklist": "https://api.deezer.com/artist/13/top?limit=50",
                  "type": "artist"
                },
                {
                  "id": "5305180",
                  "name": "Tribute to Eminem",
                  "link": "https://www.deezer.com/artist/5305180",
                  "picture": "https://api.deezer.com/artist/5305180/image",
                  "picture_small": "https://cdns-images.dzcdn.net/images/artist/40dc26ccf00d62eba8e5804325776269/56x56-000000-80-0-0.jpg",
                  "picture_medium": "https://cdns-images.dzcdn.net/images/artist/40dc26ccf00d62eba8e5804325776269/250x250-000000-80-0-0.jpg",
                  "picture_big": "https://cdns-images.dzcdn.net/images/artist/40dc26ccf00d62eba8e5804325776269/500x500-000000-80-0-0.jpg",
                  "picture_xl": "https://cdns-images.dzcdn.net/images/artist/40dc26ccf00d62eba8e5804325776269/1000x1000-000000-80-0-0.jpg",
                  "nb_album": 2,
                  "nb_fan": 1291,
                  "radio": false,
                  "tracklist": "https://api.deezer.com/artist/5305180/top?limit=50",
                  "type": "artist"
                },
                {
                  "id": "1663671",
                  "name": "Dirty Money Music Group featuring Eminem",
                  "link": "https://www.deezer.com/artist/1663671",
                  "picture": "https://api.deezer.com/artist/1663671/image",
                  "picture_small": "https://cdns-images.dzcdn.net/images/artist//56x56-000000-80-0-0.jpg",
                  "picture_medium": "https://cdns-images.dzcdn.net/images/artist//250x250-000000-80-0-0.jpg",
                  "picture_big": "https://cdns-images.dzcdn.net/images/artist//500x500-000000-80-0-0.jpg",
                  "picture_xl": "https://cdns-images.dzcdn.net/images/artist//1000x1000-000000-80-0-0.jpg",
                  "nb_album": 0,
                  "nb_fan": 4249,
                  "radio": false,
                  "tracklist": "https://api.deezer.com/artist/1663671/top?limit=50",
                  "type": "artist"
                }
            ]
    """.trimIndent()

    val artistJSON = """
        {
          "id": "27",
          "name": "Daft Punk",
          "link": "https://www.deezer.com/artist/27",
          "share": "https://www.deezer.com/artist/27?utm_source=deezer&utm_content=artist-27&utm_term=0_1599799550&utm_medium=web",
          "picture": "https://api.deezer.com/artist/27/image",
          "picture_small": "https://e-cdns-images.dzcdn.net/images/artist/f2bc007e9133c946ac3c3907ddc5d2ea/56x56-000000-80-0-0.jpg",
          "picture_medium": "https://e-cdns-images.dzcdn.net/images/artist/f2bc007e9133c946ac3c3907ddc5d2ea/250x250-000000-80-0-0.jpg",
          "picture_big": "https://e-cdns-images.dzcdn.net/images/artist/f2bc007e9133c946ac3c3907ddc5d2ea/500x500-000000-80-0-0.jpg",
          "picture_xl": "https://e-cdns-images.dzcdn.net/images/artist/f2bc007e9133c946ac3c3907ddc5d2ea/1000x1000-000000-80-0-0.jpg",
          "nb_album": 31,
          "nb_fan": 3832916,
          "radio": true,
          "tracklist": "https://api.deezer.com/artist/27/top?limit=50",
          "type": "artist"
        }
    """.trimIndent()

}