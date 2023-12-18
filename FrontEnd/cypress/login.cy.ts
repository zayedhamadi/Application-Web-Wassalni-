describe('Login Page Test', () => {
  it('Visits the login page', () => {
    cy.visit('http://localhost:4200/login');




    cy.get('#email').type('user@example.com');
    cy.get('#password').type('password123');
    cy.get('form').submit();


  });
});
//npx cypress open --component --browser=edge