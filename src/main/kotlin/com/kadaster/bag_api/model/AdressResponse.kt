package com.kadaster.bag_api.model

import com.fasterxml.jackson.annotation.JsonProperty

data class AddressResponse(
    val adressen: List<Address>
)

data class Address(
    val openbareRuimteNaam: String,
    val korteNaam: String,
    val huisnummer: Int,
    val huisletter: String?,
    val huisnummertoevoeging: String?,
    val postcode: String,
    val woonplaatsNaam: String,
    val nummeraanduidingIdentificatie: String,
    val openbareRuimteIdentificatie: String,
    val woonplaatsIdentificatie: String,
    val adresseerbaarObjectIdentificatie: String,
    val pandIdentificaties: List<String> = emptyList(),
    val indicatieNevenadres: Boolean,
    val adresregel5: String,
    val adresregel6: String,
    val geconstateerd: Geconstateerd,
    val inonderzoek: InOnderzoek,
    @JsonProperty("_links") val links: Links,
    @JsonProperty("_embedded") val embedded: Any? = null
)
