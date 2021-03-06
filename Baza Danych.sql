SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cennik](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[IdTowar] [int] NOT NULL,
	[Cena] [money] NOT NULL,
	[DataPoczatkowa] [date] NOT NULL,
	[DataKoncowa] [date] NULL,
	[Typ] [int] NULL,
 CONSTRAINT [PK_Cennik] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[CennikIndywidualny]    Script Date: 2016-02-22 18:56:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CennikIndywidualny](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[IdTowar] [int] NOT NULL,
	[IdKlient] [int] NOT NULL,
	[Cena] [money] NOT NULL,
	[DataPoczatkowa] [date] NOT NULL,
	[DataKoncowa] [date] NULL,
 CONSTRAINT [PK_CennikIndywidualny] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Dostawa]    Script Date: 2016-02-22 18:56:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Dostawa](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Numer] [nvarchar](50) NULL,
	[IdDostawca] [int] NOT NULL,
	[Data] [date] NOT NULL,
	[Kwota] [money] NOT NULL,
	[stan] [int] NULL CONSTRAINT [DF_Dostawa_stan]  DEFAULT ((0)),
 CONSTRAINT [PK_Dostawa] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Dostawca]    Script Date: 2016-02-22 18:56:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Dostawca](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Nazwa] [nvarchar](500) NOT NULL,
	[NIP] [nvarchar](13) NOT NULL,
	[NrBudynku] [nvarchar](50) NOT NULL,
	[NrLokalu] [nvarchar](50) NULL,
	[Ulica] [nvarchar](160) NOT NULL,
	[KodPocztowy] [nvarchar](6) NOT NULL,
	[Miejscowosc] [nvarchar](160) NOT NULL,
	[Notatka] [text] NULL,
	[kod] [nvarchar](50) NULL,
 CONSTRAINT [PK_Dostawca] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Faktura]    Script Date: 2016-02-22 18:56:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Faktura](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[numer] [nvarchar](50) NOT NULL,
	[DataWystawienia] [date] NOT NULL,
	[TerminZaplaty] [date] NOT NULL,
	[FormaZaplaty] [text] NOT NULL,
	[Uwagi] [text] NULL,
	[Notatki] [text] NULL,
	[IdKlient] [int] NOT NULL,
	[KlientNazwa] [nvarchar](150) NULL,
	[KlintNIP] [nvarchar](50) NULL,
	[KlientAdres] [nvarchar](150) NULL,
	[wartoscBrutto] [float] NULL,
	[editable] [int] NULL CONSTRAINT [DF_Faktura_editable]  DEFAULT ((1)),
	[DataSprzedazy] [date] NULL,
	[wydano] [int] NULL CONSTRAINT [DF_Faktura_wydano]  DEFAULT ((0)),
 CONSTRAINT [PK_Faktura] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Firma]    Script Date: 2016-02-22 18:56:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Firma](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Nazwa] [nvarchar](500) NOT NULL,
	[NIP] [nvarchar](13) NOT NULL,
	[NrBudynku] [nvarchar](50) NOT NULL,
	[NrLokalu] [nvarchar](50) NULL,
	[Ulica] [nvarchar](160) NOT NULL,
	[KodPocztowy] [nvarchar](6) NOT NULL,
	[Miejscowosc] [nvarchar](160) NOT NULL,
	[Notatka] [text] NULL,
	[KodKlienta] [nvarchar](500) NULL,
 CONSTRAINT [PK_Firma] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Hurtownia]    Script Date: 2016-02-22 18:56:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Hurtownia](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[NazwaHurtownia] [text] NOT NULL,
	[NIPHurtownia] [nchar](15) NOT NULL,
	[AdresHurtownia] [text] NOT NULL,
	[MiejscowoscHurtownia] [nvarchar](150) NOT NULL,
 CONSTRAINT [PK_Hurtownia] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[JednostkaMiary]    Script Date: 2016-02-22 18:56:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[JednostkaMiary](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Nazwa] [nvarchar](50) NOT NULL,
	[skrot] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_JednostkaMiary_1] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Kategoria]    Script Date: 2016-02-22 18:56:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Kategoria](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Nazwa] [nchar](10) NOT NULL,
 CONSTRAINT [PK_Kategoria] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[PozycjaDostawy]    Script Date: 2016-02-22 18:56:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PozycjaDostawy](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Towar] [int] NOT NULL,
	[ilosc] [float] NOT NULL,
	[Cena] [float] NOT NULL,
	[Wartosc] [float] NOT NULL,
	[IdDostawa] [int] NOT NULL,
	[IloscPozostala] [float] NULL,
 CONSTRAINT [PK_PozycjaDostawy] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[PozycjaFaktury]    Script Date: 2016-02-22 18:56:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PozycjaFaktury](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Towar] [int] NOT NULL,
	[IdFaktura] [int] NOT NULL,
	[ilosc] [nchar](10) NOT NULL,
	[CenaNetto] [money] NOT NULL,
	[VAT] [float] NOT NULL,
	[CenaBrutto] [money] NULL,
	[jm] [nvarchar](50) NULL,
	[PKWiU] [nvarchar](20) NULL,
	[CenaWydanie] [money] NULL,
 CONSTRAINT [PK_PozycjaFaktury] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Towar]    Script Date: 2016-02-22 18:56:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Towar](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Nazwa] [nvarchar](50) NOT NULL,
	[Opis] [text] NULL,
	[JednostkaMiary] [int] NULL,
	[KodTowaru] [nvarchar](50) NOT NULL,
	[KodKreskowy] [nvarchar](50) NOT NULL,
	[PKWiU] [nvarchar](50) NOT NULL,
	[IdKategoria] [int] NULL,
	[VAT] [int] NULL,
	[Ilosc] [float] NULL CONSTRAINT [DF_Towar_Ilosc]  DEFAULT ((0)),
	[dostepne] [float] NULL CONSTRAINT [DF_Towar_dostepne]  DEFAULT ((0.0)),
 CONSTRAINT [PK_Towar] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Uzytkownik]    Script Date: 2016-02-22 18:56:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Uzytkownik](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Login] [varchar](50) NOT NULL,
	[Haslo] [varchar](50) NOT NULL,
	[Imie] [varchar](50) NOT NULL,
	[Nazwisko] [varchar](50) NOT NULL,
	[Grupa] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Uzytkownik] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[Cennik]  WITH NOCHECK ADD  CONSTRAINT [FK_Cennik_Towar] FOREIGN KEY([IdTowar])
REFERENCES [dbo].[Towar] ([Id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Cennik] NOCHECK CONSTRAINT [FK_Cennik_Towar]
GO
ALTER TABLE [dbo].[CennikIndywidualny]  WITH NOCHECK ADD  CONSTRAINT [FK_CennikIndywidualny_Firma] FOREIGN KEY([IdKlient])
REFERENCES [dbo].[Firma] ([Id])
GO
ALTER TABLE [dbo].[CennikIndywidualny] NOCHECK CONSTRAINT [FK_CennikIndywidualny_Firma]
GO
ALTER TABLE [dbo].[CennikIndywidualny]  WITH NOCHECK ADD  CONSTRAINT [FK_CennikIndywidualny_Towar1] FOREIGN KEY([IdTowar])
REFERENCES [dbo].[Towar] ([Id])
GO
ALTER TABLE [dbo].[CennikIndywidualny] NOCHECK CONSTRAINT [FK_CennikIndywidualny_Towar1]
GO
ALTER TABLE [dbo].[Dostawa]  WITH NOCHECK ADD  CONSTRAINT [FK_Dostawa_Dostawca] FOREIGN KEY([IdDostawca])
REFERENCES [dbo].[Dostawca] ([Id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Dostawa] NOCHECK CONSTRAINT [FK_Dostawa_Dostawca]
GO
ALTER TABLE [dbo].[Faktura]  WITH NOCHECK ADD  CONSTRAINT [FK_Faktura_Firma] FOREIGN KEY([IdKlient])
REFERENCES [dbo].[Firma] ([Id])
GO
ALTER TABLE [dbo].[Faktura] NOCHECK CONSTRAINT [FK_Faktura_Firma]
GO
ALTER TABLE [dbo].[PozycjaDostawy]  WITH NOCHECK ADD  CONSTRAINT [FK_PozycjaDostawy_Dostawa] FOREIGN KEY([IdDostawa])
REFERENCES [dbo].[Dostawa] ([Id])
GO
ALTER TABLE [dbo].[PozycjaDostawy] NOCHECK CONSTRAINT [FK_PozycjaDostawy_Dostawa]
GO
ALTER TABLE [dbo].[PozycjaDostawy]  WITH NOCHECK ADD  CONSTRAINT [FK_PozycjaDostawy_Towar] FOREIGN KEY([Towar])
REFERENCES [dbo].[Towar] ([Id])
GO
ALTER TABLE [dbo].[PozycjaDostawy] NOCHECK CONSTRAINT [FK_PozycjaDostawy_Towar]
GO
ALTER TABLE [dbo].[PozycjaFaktury]  WITH NOCHECK ADD  CONSTRAINT [FK_PozycjaFaktury_Faktura] FOREIGN KEY([IdFaktura])
REFERENCES [dbo].[Faktura] ([Id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[PozycjaFaktury] NOCHECK CONSTRAINT [FK_PozycjaFaktury_Faktura]
GO
ALTER TABLE [dbo].[PozycjaFaktury]  WITH NOCHECK ADD  CONSTRAINT [FK_PozycjaFaktury_Towar] FOREIGN KEY([Towar])
REFERENCES [dbo].[Towar] ([Id])
GO
ALTER TABLE [dbo].[PozycjaFaktury] NOCHECK CONSTRAINT [FK_PozycjaFaktury_Towar]
GO
ALTER TABLE [dbo].[Towar]  WITH CHECK ADD  CONSTRAINT [FK_Towar_JednostkaMiary] FOREIGN KEY([JednostkaMiary])
REFERENCES [dbo].[JednostkaMiary] ([Id])
GO
ALTER TABLE [dbo].[Towar] CHECK CONSTRAINT [FK_Towar_JednostkaMiary]
GO
ALTER TABLE [dbo].[Towar]  WITH CHECK ADD  CONSTRAINT [FK_Towar_Kategoria] FOREIGN KEY([IdKategoria])
REFERENCES [dbo].[Kategoria] ([Id])
GO
ALTER TABLE [dbo].[Towar] CHECK CONSTRAINT [FK_Towar_Kategoria]
GO
