package com.order.application.service

import com.order.application.port.out.OrderCommandPort
import com.order.model.Order
import com.order.model.Payment
import com.order.model.Product
import com.order.model.User
import com.order.share.PaymentMeans
import com.order.share.UidCreation
import com.order.usecase.OrderCreationRequest
import com.order.usecase.OrderCreationUseCase
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class OrderCreationServiceTest(
    private val orderCommandPort: OrderCommandPort = mockk(),
    private val orderCreationUseCase: OrderCreationUseCase = mockk(),
) : BehaviorSpec({
    isolationMode = IsolationMode.InstancePerLeaf

    val sut = OrderCreationService(orderCommandPort, orderCreationUseCase)

    val seller = User(
        username = "gose",
        email = "gose@naver.com",
        password = "123456",
        phoneNumber = "010-0000-1111",
    )

    val buyer = User(
        username = "kose",
        email = "kose@naver.com",
        password = "123456",
        phoneNumber = "010-1111-2222",
    )

    val product = Product(
        name = "Book",
        price = 1000L,
        stock = 1000L,
        seller = seller,
    )

    val payment = Payment(
        price = 1000L,
        means = PaymentMeans.CREDIT_CARD,
    )

    given("OrderCreationRequest가 주어져요") {
        val orderCreationRequest = OrderCreationRequest(
            user = buyer,
            product = product,
            payment = payment,
        )

        val mockOrder = Order(
            txId = UidCreation.creationUid(),
            user = buyer,
            product = product,
            payment = payment,
        )

        every { orderCreationUseCase.createOrder(any()) } returns mockOrder
        every { orderCommandPort.saveOrder(any()) } returns 1L

        `when`("상품 저장을 요청하면") {
            val result = sut.creationOrder(orderCreationRequest)

            then("상품은 저장되고 상품 id가 리턴되어야 해요")
            result shouldBe 1L
        }
    }
})
