describe("Cart test", () => {
  let productId;
  let cartId;
  let itemId;
  const quantity = 1;
  const updatedQuantity = 10;

  before(() => {
    cy.request("POST", "http://localhost:1323/products", {
      name: "Test product",
      price: 1.23,
    }).then((response) => {
      productId = response.body.ID;
    });
  });

  it("should add new cart", () => {
    cy.request("POST", "http://localhost:1323/carts").then((response) => {
      expect(response.status).to.eq(201);
      expect(response.body).to.have.property("ID");
      cartId = response.body.ID;
      expect(response.body).to.have.property("CartItems");
      expect(response.body.CartItems).to.eq(null);
    });
  });

  it("should return all carts", () => {
    cy.request("GET", "http://localhost:1323/carts").then((response) => {
      expect(response.status).to.eq(200);
      expect(response.body).to.be.an("array");
      expect(response.body.length).to.be.greaterThan(0);
    });
  });

  it("should add new item to cart", () => {
    cy.request("POST", `http://localhost:1323/carts/${cartId}/items`, {
      product_id: productId,
      quantity,
    }).then((response) => {
      expect(response.status).to.eq(201);
      expect(response.body).to.have.property("ID");
      itemId = response.body.ID;
    });
  });

  it("should return items list", () => {
    cy.request("GET", `http://localhost:1323/carts/${cartId}`).then(
      (response) => {
        expect(response.status).to.eq(200);
        expect(response.body.CartItems).to.be.an("array");
        expect(response.body.CartItems.length).to.be.greaterThan(0);
        cy.wrap(response.body.CartItems).then((array) => {
          const item = array.find((obj) => obj.ID == itemId);
          expect(item).to.have.property("quantity");
          expect(item.quantity).to.eq(quantity);
        });
      },
    );
  });

  it("should update cart item", () => {
    cy.request("PUT", `http://localhost:1323/carts/${cartId}/items/${itemId}`, {
      product_id: productId,
      quantity: updatedQuantity,
    }).then((response) => {
      expect(response.status).to.eq(200);
      expect(response.body).to.have.property("ID");
      expect(response.body.quantity).to.eq(updatedQuantity);
    });
  });

  it("should return items list with updated item", () => {
    cy.request("GET", `http://localhost:1323/carts/${cartId}`).then(
      (response) => {
        expect(response.status).to.eq(200);
        expect(response.body.CartItems).to.be.an("array");
        expect(response.body.CartItems.length).to.be.greaterThan(0);
        cy.wrap(response.body.CartItems).then((array) => {
          const item = array.find((obj) => obj.ID == itemId);
          expect(item).to.have.property("quantity");
          expect(item.quantity).to.eq(updatedQuantity);
        });
      },
    );
  });

  it("should delete item from cart", () => {
    cy.request(
      "DELETE",
      `http://localhost:1323/carts/${cartId}/items/${itemId}`,
    ).then((response) => {
      expect(response.status).to.eq(204);
    });
  });

  it("should return items list without deleted item", () => {
    cy.request("GET", `http://localhost:1323/carts/${cartId}`).then(
      (response) => {
        expect(response.status).to.eq(200);
        expect(response.body.CartItems).to.be.an("array");
        expect(response.body.CartItems.length).to.eq(0);
      },
    );
  });

  it("should return error when updating deleted item", () => {
    cy.request(
      {
        method: "PUT",
        url: `http://localhost:1323/carts/${cartId}/items/${itemId}`,
        failOnStatusCode: false,
      },
      {
        product_id: productId,
        quantity: updatedQuantity,
      },
    ).then((response) => {
      expect(response.status).to.eq(404);
    });
  });

  it("should return error for invalid cart id", () => {
    cy.request({
      method: "GET",
      url: "http://localhost:1323/carts/-1",
      failOnStatusCode: false,
    }).then((response) => {
      expect(response.status).to.eq(404);
      expect(response.body).to.eq("record not found");
    });
  });

  it("should delete cart", () => {
    cy.request("DELETE", `http://localhost:1323/carts/${cartId}`).then(
      (response) => {
        expect(response.status).to.eq(204);
      },
    );
  });

  it("should return error for deleted cart", () => {
    cy.request({
      method: "GET",
      url: `http://localhost:1323/carts/${cartId}`,
      failOnStatusCode: false,
    }).then((response) => {
      expect(response.status).to.eq(404);
      expect(response.body).to.eq("record not found");
    });
  });
});
