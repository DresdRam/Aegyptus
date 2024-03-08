package sq.mayv.aegyptus.util

interface PermissionTextProvider {
    fun getDescription(isPermanentlyDeclined: Boolean): String
}