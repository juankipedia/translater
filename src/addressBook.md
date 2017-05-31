parsed: Map("address book" -> Map("name" -> "john smith", "address" -> Map("street" -> "10 Market street", "city" -> "San Francisco, CA", "zip" -> 94111.0), "phone numbers" -> List("04140802832", "04149798710")), "value" -> 333.0)
parsed: Map("address book" -> Map("name" -> "john smith", "address" -> Map("street" -> "10 Market street", "city" -> "San Francisco, CA", "zip" -> 94111.0), "phone numbers" -> List("04140802832", "04149798710")), "value" -> 333.0)
# ObjectDefinition
  ## "address book":
       # ObjectDefinition
            ## "name":
                "john smith".
            ## "address":
                # ObjectDefinition
                  ## "street":
                    "10 Market street".
                  ## "city":
                    "San Francisco, CA".
                  ## "zip":
                    94111
                |
            .
            ## "phone numbers":
            *"04140802832",
             "04149798710"*
      |
  .
  ## "value": 333
|