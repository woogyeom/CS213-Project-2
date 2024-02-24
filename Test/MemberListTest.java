import org.junit.Test;

import static org.junit.Assert.*;

public class MemberListTest {

    private MemberList memberList;
    private Member basicMember;
    private Member familyMember;
    private Member premiumMember;

    @Test
    public void testAddTrue_BasicMember() {
        memberList = new MemberList();
        basicMember = new Basic(new Profile("John", "Doe", new Date(1, 1, 1990)), new Date(12, 31, 2023), Location.EDISON);
        assertTrue(memberList.add(basicMember));
    }

    @Test
    public void testAddTrue_FamilyMember() {
        memberList = new MemberList();
        familyMember = new Family(new Profile("Jane", "Doe", new Date(2, 2, 1991)), new Date(12, 31, 2023), Location.BRIDGEWATER);
        assertTrue(memberList.add(familyMember));
    }

    @Test
    public void testAddTrue_PremiumMember() {
        memberList = new MemberList();
        premiumMember = new Premium(new Profile("Jake", "Doe", new Date(3, 3, 1992)), new Date(12, 31, 2023), Location.FRANKLIN);
        assertTrue(memberList.add(premiumMember));
    }

    @Test
    public void testAddFalse_BasicMemberAlreadyAdded() {
        memberList = new MemberList();
        basicMember = new Basic(new Profile("John", "Doe", new Date(1, 1, 1990)), new Date(12, 31, 2023), Location.EDISON);
        memberList.add(basicMember);
        assertFalse(memberList.add(basicMember));
    }

    @Test
    public void testAddFalse_FamilyMemberAlreadyAdded() {
        memberList = new MemberList();
        familyMember = new Family(new Profile("Jane", "Doe", new Date(2, 2, 1991)), new Date(12, 31, 2023), Location.BRIDGEWATER);
        memberList.add(familyMember);
        assertFalse(memberList.add(familyMember));
    }

    @Test
    public void testAddFalse_PremiumMemberAlreadyAdded() {
        memberList = new MemberList();
        premiumMember = new Premium(new Profile("Jake", "Doe", new Date(3, 3, 1992)), new Date(12, 31, 2023), Location.FRANKLIN);
        memberList.add(premiumMember);
        assertFalse(memberList.add(premiumMember));
    }

    @Test
    public void testRemoveTrue_RandomMember() {
        memberList = new MemberList();
        basicMember = new Basic(new Profile("John", "Doe", new Date(1, 1, 1990)), new Date(12, 31, 2023), Location.EDISON);
        memberList.add(basicMember);
        assertTrue(memberList.remove(basicMember));
    }

    @Test
    public void testRemoveFalse_MemberNotPresent() {
        memberList = new MemberList();
        basicMember = new Basic(new Profile("John", "Doe", new Date(1, 1, 1990)), new Date(12, 31, 2023), Location.EDISON);
        // Attempting to remove a member that was never added
        assertFalse(memberList.remove(basicMember));
    }
}