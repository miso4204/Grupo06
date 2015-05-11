Ecoturismo : User ShoppingCart Pay Reports [SpecialOffers] [SocialNetwork] [AdminMoneda] Product QualityAtributes GUI :: _Ecoturismo ;

User : Session UpdateProfile CreateAccount :: _User ;

Session : LogIn LogOut :: _Session ;

UpdateProfile : [ChangePassword] [ChangeAdress] :: _UpdateProfile ;

Pay : CreditCard PSE [CashOnDelivery] :: _Pay ;

Reports : [Rating] [Sales] :: _Reports ;

Rating : Location [Package] :: _Rating ;

Sales : [ReportByLocation] [ReportByPeriod] :: _Sales ;

SpecialOffers : [CreatePromo] [UpdatePromo] :: _SpecialOffers ;

SocialNetwork : Facebook
	| Twitter ;

AdminMoneda : Dolar [Euro] [Colombian] :: _AdminMoneda ;

Product : Search :: _Product ;

Search : [ByLocation] ByPrice ByDate :: _Search ;

QualityAtributes : Configurability [Scalability] Functionality [Performance] :: _QualityAtributes ;

GUI : Base
	| Intermediate
	| Advance ;

%%

Base implies not  (ChangePassword or CashOnDelivery or Package or Sales or SpecialOffers or SocialNetwork or AdminMoneda or ByLocation or Scalability or Performance) ;
Advance implies CreatePromo and ChangeAdress and ChangePassword and CashOnDelivery and Sales and ReportByLocation and ReportByPeriod and Rating and Package and SpecialOffers and CreateAccount and UpdatePromo and SocialNetwork and Facebook and AdminMoneda and Euro and Colombian and ByLocation and Scalability and Performance ;
Intermediate implies not  (Sales or SpecialOffers or AdminMoneda or Scalability or Performance) ;
Base implies ChangeAdress and Rating ;
Intermediate implies ChangePassword and CashOnDelivery and ChangeAdress and Rating and Package and SocialNetwork and Twitter and ByLocation ;

