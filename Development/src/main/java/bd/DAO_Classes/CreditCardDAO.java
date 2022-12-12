package bd.DAO_Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bd.utils.CardType;
import fc.clients.cards.CreditCard;

public class CreditCardDAO extends DAO<CreditCard> {

    public CreditCardDAO(Connection conn) {
	super(conn);
    }

    /**
     * Getter of a CreditCard, selected by its id.
     * @author MathysC
     * @param id the ID of the Credit Card.
     * @return CreditCard the CreditCard or null if not exists.
     */
    @Override
    public CreditCard read(int id) {
	CreditCard card = null;
	String readQuery = "SELECT * FROM CreditCards WHERE CreditID = ?";
        try(PreparedStatement stmt = this.connect.prepareStatement(readQuery)){
            stmt.setInt(1, id);
            
            ResultSet result = stmt.executeQuery();
            if(result.first()) {
        		card = new CreditCard(result.getInt("supportCardID"));
            }
        }
        catch (SQLException e) { e.printStackTrace(); }
        return card;
    }

    /**
     * Insert a CreditCard in the database.
     * First insert a SupportCard related to the CreditCard
     * Then insert a CreditCard.
     * @author MathysC
     * @param CreditCard the CreditCard to insert.
     * @return true if the CreditCard is inserted successfully. Else false.
     */
    @Override
    public boolean create(CreditCard card) {
	boolean success = false;
	String suppIDQuery = "SELECT supportCardseq.nextval AS suppID FROM DUAL";
	String creditcardIDQuery = "SELECT creditCardseq.nextval AS creditID FROM DUAL";
	String createSupportCardQuery = "INSERT INTO SupportCards(SUPPORTCARDID, TYPECARD) VALUES (?, ?)";
	String createCreditCardQuery = "INSERT INTO CreditCards(CREDITID, SUPPORTCARDID, CREDITCARDNUM) VALUES (?, ?, ?)";
	try (ResultSet resultSuppID = this.connect.createStatement().executeQuery(suppIDQuery);
		ResultSet resultCardID = this.connect.createStatement().executeQuery(creditcardIDQuery);
		PreparedStatement createSupportCard = this.connect.prepareStatement(createSupportCardQuery);
		PreparedStatement createCreditCard = this.connect.prepareStatement(createCreditCardQuery)
			)
	
	{
	    // Check if we get the correct IDs.
	    if(!resultCardID.first() || !resultSuppID.first()) {
		throw new SQLException("Sequence id unexpectedly interrupted");
	    }
	    
	    // Get IDs
	    int supportID = resultSuppID.getInt("suppID");
	    int creditID = resultCardID.getInt("creditID");
	    
	    // Insert a supportCard
	    createSupportCard.setInt(1, supportID);
	    createSupportCard.setString(2, CardType.CREDIT.toString());
	    createSupportCard.executeUpdate();
	    
	    
	    // Insert a creditCard
	    createCreditCard.setInt(1, creditID);
	    createCreditCard.setInt(2, supportID);
	    createSupportCard.setInt(2, card.getNumber());
	    createSupportCard.executeUpdate();

	    success = true;
	    
	} 
	catch (SQLException e) { 
	    e.printStackTrace(); 
	    success = false;
	}
	
	return success;
    }

    @Override
    public boolean update(CreditCard obj) {
	return false;
    }

    @Override
    public boolean delete(CreditCard obj) {
	return false;
    }

}
