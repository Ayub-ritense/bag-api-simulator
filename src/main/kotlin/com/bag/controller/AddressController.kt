package com.bag.controller

import com.bag.model.AddressResponse
import com.bag.service.AddressService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/haalcentraal/api/bag/v1/adressen")
class AddressController(
    private val addressService: AddressService
) {

    @GetMapping
    fun getAddress(
        @RequestParam postcode: String,
        @RequestParam huisnummer: Int,
        @RequestParam(required = false) huisletter: String?,
        @RequestParam(required = false) huisnummertoevoeging: String?
    ): ResponseEntity<AddressResponse> {
        val addressResponse = addressService.getAddress(
            postcode, huisnummer, huisletter, huisnummertoevoeging
        )
        return addressResponse?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()
    }
}
