package com.bag.repository


import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.bag.model.Address
import com.bag.model.AddressResponse
import com.bag.model.Embedded
import com.bag.model.Link
import com.bag.model.Links
import org.springframework.stereotype.Repository

@Repository
class AddressRepository {

    private val addresses: List<Address> by lazy { loadAddressesFromJson() }

    fun findByPostcodeAndHuisnummer(
        postcode: String,
        huisnummer: Int,
        huisletter: String?,
        huisnummertoevoeging: String?
    ): AddressResponse? {

        val normalizedPostcode = normalize(postcode)
        val normalizedHuisletter = normalize(huisletter)
        val normalizedToevoeging = normalize(huisnummertoevoeging)

        val matchingAddresses = addresses.filter { address ->
            normalize(address.postcode) == normalizedPostcode &&
                    address.huisnummer == huisnummer &&
                    (normalizedHuisletter == null || normalize(address.huisletter) == normalizedHuisletter) &&
                    (normalizedToevoeging == null || normalize(address.huisnummertoevoeging) == normalizedToevoeging)
        }

        return matchingAddresses.takeIf { it.isNotEmpty() }?.let {
            AddressResponse(
                embedded = Embedded(it),
                links = Links(self = Link("https://api.bag.nl/adressen"))
            )
        }
    }

    private fun loadAddressesFromJson(): List<Address> {
        val resource = requireNotNull(this::class.java.getResource("/fixtures/address.json")) {
            "Address JSON file not found in classpath"
        }
        val typeRef = object : TypeReference<List<Address>>() {}
        return objectMapper.readValue(resource, typeRef)
    }

    private fun normalize(input: String?): String? {
        return input
            ?.lowercase()
            ?.replace("[^a-z0-9]".toRegex(), "") // removes special characters and whitespace
    }

    companion object {
        private val objectMapper = jacksonObjectMapper().findAndRegisterModules()
    }
}
