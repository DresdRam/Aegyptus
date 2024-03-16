package sq.mayv.aegyptus.util

import sq.mayv.aegyptus.data.IPermissionTextProvider

class LocationPermissionTextProvider : IPermissionTextProvider {
    override fun getDescription(): String {
        return "Aegyptus needs access to your location in order to work properly."
    }
}