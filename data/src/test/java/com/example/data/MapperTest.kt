package com.example.data

import com.example.data.mappers.toDomain
import com.example.data.models.emptyPixImage
import com.example.data.models.mockPixImage
import com.example.domain.models.emptyImageModel
import com.example.domain.models.imageModelMock
import org.junit.Assert
import org.junit.Test

class MapperTest {
    @Test
    fun check_Mapper_empty_image() {
        // GIVEN
        val dataModel = emptyPixImage

        // WHEN
        val domainModel = dataModel.toDomain()

        // THEN
        Assert.assertEquals(emptyImageModel, domainModel)
    }

    @Test
    fun check_Mapper_image() {
        // GIVEN
        val dataModel = mockPixImage

        // WHEN
        val domainModel = dataModel.toDomain()

        // THEN
        Assert.assertEquals(imageModelMock, domainModel)
    }
}