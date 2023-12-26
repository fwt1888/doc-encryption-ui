# doc-encryption-ui
A UI interface that can implement basic encryption and decryption functions. (Note: The interface language is Chinese)<br>
It simulates the scenario of data transmission between multiple users, and can customize the identity (receiver or sender), and finally realizes the signature encryption process of strings and files.<br>

**Identity Management:** Each identity is bound to a pair of RSA public and private keys. If the receiver needs to decrypt the data, the sender only needs to provide the .pem file corresponding to the public key.<br>
**Data encryption/decryption:** Optional symmetric encryption algorithms include DES and AES; symmetric encryption keys can be customized or automatically generated by the program based on seeds; Hash algorithms include SHA and MD5. (The above functions can be changed through advanced settings)<br>

**For more details, please visit the link below:**
https://www.yuque.com/fwt1888/doc_encryption_ui
