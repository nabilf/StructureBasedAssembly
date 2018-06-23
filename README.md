# StructureBasedAssembly

## RNA genomic sequence assembly based on physical structure

Sequencing of DNA and RNA is the determination of the precise order of nucleotides, the acids or "bases" that form the actual molecular chain (represented as a string of A,C,G,T in the case of DNA and A,C,G,U in the case of RNA). The demand for computational sequencing of DNA, RNA and other nucleic acids and classifying their functions has grown with the advancement of technology that enables the reading of the sequences, because higher throughput and larger number of shorter fragments requires a greater level of automation. In practice,  strand of bases of a length greater than a few hundred cannot be read at once due to limitations in current technology, and so the strand is instead read in pieces or fragments after which the sub-process of sequence assembly is to reattach the fragments into the original form.

However, DNA and RNA are more than a string of characters. They are constructs whose existence is subject to laws of physics and forces of nature, and it is therefore reasonable to consider such factors when attempting to assemble this construct from the pieces represented by sequenced fragments. Ultimately the goal is to have a true representation –or as close to true as possible of the genome so that features and patterns can be found, rather than an optimized one. Based on that, the code in this repository is intended to implement an alternate based on physical structure to help find that true representation.

This project is an implementation of that concept in RNA sequence assembly using RNA structure considerations.

For further details regarding research:
https://unbscholar.lib.unb.ca/islandora/object/unbscholar%3A7894
