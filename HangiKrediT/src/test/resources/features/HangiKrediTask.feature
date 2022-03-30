@smoke
Feature: ihtiyac kredisi sayfasi

    @cta
    Scenario: Verify that CTA is working properly
      Given the user clicks on ihtiyacKredi button
      When the user should display ihtiyac Kredisi on the page
      And the user enters kredi tutari
      And the user selects kredi vadesi
      And  the user clicks Hesapla button
      Then the user should display  TL AY Vadeli ihtiyac Kredileri headline
      Then the user should display Hemen Basvur button
      And the user clicks Hemen Basvur Button
      Then Redirected page should be displayed properly
      Then Pageview-Event items should be present on the page

  @CardPage
  Scenario: Verify that KrediKarti page is working properly
      When  the user navigates kredi karti page
      Then  the user should land on "https://www.hangikredi.com/kredi-karti"
      When the user clicks on tum kredi kartlari button
      Then the user should be able to reach to the page of bank by clicking basvur button, except "Akbank"















