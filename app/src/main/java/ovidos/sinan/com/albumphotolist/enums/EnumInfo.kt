package ovidos.sinan.com.albumphotolist.enums

/**
 * Created by sinan on 10.03.2018.
 */
enum class EnumInfo {
    NoConnection {
        override fun toString(): String {
            return "No Internet Connection"
        }
    },
    Albums {
        override fun toString(): String {
            return "Albums"
        }
    },
    Photos {
        override fun toString(): String {
            return "Photos"
        }
    },
    AlbumId {
        override fun toString(): String {
            return "albumId"
        }
    },
    AlbumUrl {
        override fun toString(): String {
            return "http://jsonplaceholder.typicode.com/albums"
        }
    },
    PhotoUrl {
        override fun toString(): String {
            return "http://jsonplaceholder.typicode.com/photos/?albumId="
        }
    }
}