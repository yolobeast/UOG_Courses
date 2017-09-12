DROP FUNCTION q08(T_Num TEXT, V_Num TEXT, A_Num TEXT, A_Amt DECIMAL(20,2));
CREATE OR REPLACE FUNCTION q08(T_Num TEXT, V_Num TEXT, A_Num TEXT, A_Amt DECIMAL(20,2)) 
RETURNS VOID as $$
    declare
            
            Vnum TEXT;
            Vnamed TEXT;
            V_City TEXT;
            V_Vbalance DECIMAL(20,2);
            
            C_Account TEXT;
            Cnamed TEXT;
            CProvince TEXT;
            Cbalanced DECIMAL(20,2);
            C_crlimit INT;
            
            c1 cursor for SELECT vendor.Vno, Vname, City, Vbalance FROM vendor;
            c2 cursor for SELECT customer.Account, Cname, Province, Cbalance, Crlimit FROM customer;
    begin

            INSERT INTO transaction (Tno, Vno, Account, T_Date, Amount) VALUES 
                (T_Num, V_Num, A_Num, CURRENT_DATE, A_Amt);
            
            UPDATE vendor SET Vbalance = Vbalance + A_amt WHERE vendor.Vno = V_Num;
            UPDATE customer SET Cbalance = Cbalance - A_amt WHERE customer.Account = A_Num;
            
            raise notice '';
            raise notice 'New Transaction';
            raise notice 'Transaction Number: %', T_num;
            raise notice 'Vendor Number: %', V_Num;
            raise notice 'Account: %', A_Num;
            raise notice 'Date: %', CURRENT_DATE;
            raise notice 'Amount: %', A_Amt;
            raise notice '';
            
            open c1;
            loop
                fetch c1 into Vnum, Vnamed, V_City, V_Vbalance;
                exit when not found;
                raise notice 'Vendor Number: %', Vnum;
                raise notice 'Vendor Name: %', Vnamed;
                raise notice 'Vendor City: %', V_City;
                raise notice 'Vendor Balance: %', V_Vbalance;
                raise notice '';
            end loop;
            close c1;
            
            open c2;
            loop
                fetch c2 into C_Account, Cnamed, Cprovince, Cbalanced, C_crlimit;
                exit when not found;
                raise notice 'Customer Account: %', C_Account;
                raise notice 'Customer Name: %', Cnamed;
                raise notice 'Customer Province: %', Cprovince;
                raise notice 'Customer Balance: %', Cbalanced;
                raise notice 'Customer Credit Limit: %', C_crlimit;
                raise notice '';
            end loop;
            close c2;

    end;
$$ language plpgsql;
