-- =============================================
-- Author:		Chris Simmons
-- Create date: 08/20/2018
-- Description:	Material Data
-- =============================================
USE [RCX]
GO
/****** Object:  StoredProcedure [dbo].[RCXMATL001SP]    Script Date: 9/13/2019 7:40:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Chris Simmons
-- Create date: 08/20/2018
-- Description:	Material Data
-- =============================================
ALTER PROCEDURE [dbo].[RCXMATL001SP]
    @Action char(1),
	@MaterialID smallint = NULL,
	@Name varchar(30) = NULL,
	@HasCountValue char = NULL,
	@HasScrapValue char = NULL,
	@HasRedemptionValue char = NULL,
	@HasBonusValue char = NULL,
	@IsActive char = NULL

AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
	Declare @i int;
	Select @i = coalesce(MAX(MaterialID),0) + 1 FROM dbo.Material;
	IF @Action = 'A'
select distinct a.MaterialID, a.Name, a.IsActive, a.HasCountValue, a.HasScrapValue, a.HasRedemptionValue,a.HasBonusValue
, case when	g.MaterialID is not null then 1 else 0 end as found 
from
	dbo.Material A left join dbo.SiteMaterial			g on A.MaterialID = g.MaterialID order by name asc;
    ELSE IF @Action = 'B'
		SELECT * FROM dbo.Material WHERE MaterialID = @MaterialID;
    ELSE IF @Action = 'C'
		DELETE FROM dbo.Material WHERE MaterialID = @MaterialID;
	ELSE IF @Action = 'D'
	BEGIN
		IF @MaterialID = 0
		BEGIN
			IF EXISTS(SELECT name from dbo.Material where Name=@Name)
				RAISERROR('RECOVERABLE-300',16,1);
			Else
				INSERT INTO dbo.Material ( MaterialID, Name, HasCountValue, HasScrapValue, HasRedemptionValue, HasBonusValue, IsActive)
					VALUES( @i, @Name, @HasCountValue, @HasScrapValue,@HasRedemptionValue, @HasBonusValue,  @IsActive );
		END 
		ELSE
			UPDATE dbo.Material SET Name = @Name, HasCountValue = @HasCountValue, HasScrapValue = @HasScrapValue, HasRedemptionValue = @HasRedemptionValue, HasBonusValue = @HasBonusValue, IsActive = @IsActive 
			WHERE MaterialID = @MaterialID;
	END
  
END

