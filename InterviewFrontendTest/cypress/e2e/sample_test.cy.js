context('Buy item', () => {

    beforeEach(() => {
        cy.visit('https://www.saucedemo.com/')
        cy.get('#user-name').type('standard_user')
        cy.get('#password').type('secret_sauce')
        cy.get('#login-button').click()
    })
    
    it('Buys an item from the site', () => {
        // Add first item to cart
        cy.get('[data-test="add-to-cart-sauce-labs-backpack"]').click(); 

        // // Go to cart
        cy.get('[data-test="shopping-cart-link"]').click();

        // // Checkout
        cy.get('[data-test="checkout"]').click();
        cy.get('[data-test="firstName"]').type('QA'); 
        cy.get('[data-test="lastName"]').type('Tester'); 
        cy.get('[data-test="postalCode"]').type('12345'); 
        cy.get('[data-test="continue"]').click(); 

        // // Finish order
        cy.get('[data-test="finish"]').click(); 

        // Assert success
        cy.contains('Thank you for your order!').should('be.visible')
    })
})