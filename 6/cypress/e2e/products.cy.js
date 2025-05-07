describe("Product test", () => {
  let productId;
  const name = "Test product";
  const price = 1.23;
  const newName = "Updated test product";
  const newPrice = 2.34;
  it("should add new product", () => {
    cy.request("POST", "http://localhost:1323/products", {
      name,
      price,
    }).then((response) => {
      expect(response.status).to.eq(201);
      expect(response.body).to.have.property("ID");
      productId = response.body.ID;
      expect(response.body.name).to.eq(name);
      expect(response.body.price).to.eq(price);
    });
  });

  it("should return product list", () => {
    cy.request("GET", "http://localhost:1323/products").then((response) => {
      expect(response.status).to.eq(200);
      expect(response.body).to.be.an("array");
      expect(response.body.length).to.be.greaterThan(0);
    });
  });

  it("should return product details", () => {
    cy.request("GET", `http://localhost:1323/products/${productId}`).then(
      (response) => {
        expect(response.status).to.eq(200);
        expect(response.body.ID).to.eq(productId);
        expect(response.body.name).to.eq(name);
        expect(response.body.price).to.eq(price);
      },
    );
  });

  it("should return error for invalid id", () => {
    cy.request({
      method: "GET",
      url: "http://localhost:1323/products/-1",
      failOnStatusCode: false,
    }).then((response) => {
      expect(response.status).to.eq(404);
      expect(response.body).to.eq("record not found");
    });
  });

  it("should update product", () => {
    cy.request("PUT", `http://localhost:1323/products/${productId}`, {
      name: newName,
      price: newPrice,
    }).then((response) => {
      expect(response.status).to.eq(200);
      expect(response.body.ID).to.eq(productId);
      expect(response.body.name).to.eq(newName);
      expect(response.body.price).to.eq(newPrice);
    });
  });

  it("should return updated product", () => {
    cy.request("GET", `http://localhost:1323/products/${productId}`).then(
      (response) => {
        expect(response.status).to.eq(200);
        expect(response.body.ID).to.eq(productId);
        expect(response.body.name).to.eq(newName);
        expect(response.body.price).to.eq(newPrice);
      },
    );
  });

  it("should delete product", () => {
    cy.request("DELETE", `http://localhost:1323/products/${productId}`).then(
      (response) => {
        expect(response.status).to.eq(204);
      },
    );
  });

  it("should return error for deleted product", () => {
    cy.request({
      method: "GET",
      url: `http://localhost:1323/products/${productId}`,
      failOnStatusCode: false,
    }).then((response) => {
      expect(response.status).to.eq(404);
      expect(response.body).to.eq("record not found");
    });
  });
});
